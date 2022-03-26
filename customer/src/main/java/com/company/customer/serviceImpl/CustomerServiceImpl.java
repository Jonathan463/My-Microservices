package com.company.customer.serviceImpl;

import com.company.customer.model.Customer;
import com.company.customer.model.CustomerRegistrationRequest;
import com.company.customer.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public record CustomerServiceImpl() implements CustomerService {
    @Override
    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        return customer;
    }
}
