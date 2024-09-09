package com.faceitteam.rentapp.controller;

import com.faceitteam.rentapp.model.dto.PaymentDto;
import com.faceitteam.rentapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<PaymentDto> processPayment(@RequestBody PaymentDto paymentRequest) {

        PaymentDto processedPayment = paymentService.processPayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(processedPayment);
    }
}

