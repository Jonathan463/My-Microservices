package com.company.fraud.serviceImpl;

import com.company.fraud.model.FraudCheckHistory;
import com.company.fraud.repository.FraudCheckHistoryRepository;
import com.company.fraud.service.FraudCheckService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class FraudCheckServiceImpl implements FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckServiceImpl(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    @Override
    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
