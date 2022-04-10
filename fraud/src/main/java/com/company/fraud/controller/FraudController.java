package com.company.fraud.controller;

import com.company.clients.fraud.fraud.FraudCheckResponse;
import com.company.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/fraud-check")

public class FraudController {

    private final FraudCheckService fraudCheckService;

    public FraudController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping(path="{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
