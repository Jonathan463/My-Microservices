package com.company.customer.service;

import com.company.customer.model.Customer;
import com.company.customer.model.CustomerRegistrationRequest;

public interface CustomerService {
    public Customer registerCustomer(CustomerRegistrationRequest request);
}
