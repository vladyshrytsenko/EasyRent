package com.faceitteam.rentapp.service;

import com.faceitteam.rentapp.model.dto.PaymentDto;

public interface PaymentService {

    PaymentDto processPayment(PaymentDto paymentRequest);
}

