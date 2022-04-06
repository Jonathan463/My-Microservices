package com.company.customer.serviceImpl;

import com.company.customer.model.Customer;
import com.company.customer.model.CustomerRegistrationRequest;
import com.company.customer.repository.CustomerRepository;
import com.company.customer.service.CustomerService;
import com.company.customer.utility.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(RestTemplate restTemplate, CustomerRepository customerRepository) {
        this.restTemplate = restTemplate;
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: check if email not taken
        customerRepository.saveAndFlush(customer);
        //todo: check if fraudster
        try {
            FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());

            if(fraudCheckResponse.isFraudster()){
                throw new IllegalStateException("fraudster");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return customer;
    }
}
