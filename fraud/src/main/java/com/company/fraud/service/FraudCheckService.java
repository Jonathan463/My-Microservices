package com.company.fraud.service;

import org.springframework.stereotype.Service;

@Service
public interface FraudCheckService {
    public boolean isFraudulentCustomer(Integer customerId);
}
