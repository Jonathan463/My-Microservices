package com.company.customer.serviceImpl;

import com.company.amqp.RabbitMQMessageProducer;
import com.company.clients.fraud.fraud.FraudCheckResponse;
import com.company.clients.fraud.fraud.FraudClient;
import com.company.clients.fraud.notification.NotificationClient;
import com.company.clients.fraud.notification.NotificationRequest;
import com.company.customer.model.Customer;
import com.company.customer.model.CustomerRegistrationRequest;
import com.company.customer.repository.CustomerRepository;
import com.company.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public CustomerServiceImpl(RestTemplate restTemplate, CustomerRepository customerRepository, FraudClient fraudClient, NotificationClient notificationClient, RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.restTemplate = restTemplate;
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }


    @Override
    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
try {

            NotificationRequest notificationRequest = new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            customer.getLastName(),
            String.format("Hi %s, welcome to my Microservice...", customer.getFirstName()));

            rabbitMQMessageProducer.publish(
                    notificationRequest,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );
    //notificationClient.sendNotification(notificationRequest);
    customerRepository.saveAndFlush(customer);
}
catch (Exception e){
    System.err.println(e);
}
        //todo: check if fraudster
//        try {
//            FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());
//
//            if(fraudCheckResponse.isFraudster()){
//                throw new IllegalStateException("fraudster");
//            }
//        } catch (Exception e){
//            System.err.println(e.getMessage());
//        }

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        return customer;
    }
}
