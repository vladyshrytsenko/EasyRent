package com.faceitteam.rentapp.service.impl;

import com.faceitteam.rentapp.model.dto.BookingDto;
import com.faceitteam.rentapp.model.dto.PaymentDto;
import com.faceitteam.rentapp.model.entity.Payment;
import com.faceitteam.rentapp.repository.PaymentRepository;
import com.faceitteam.rentapp.service.BookingService;
import com.faceitteam.rentapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final BookingService bookingService;
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentDto processPayment(PaymentDto paymentRequest) {
        BookingDto booking = bookingService.getById(paymentRequest.getBooking().getId());

        Payment payment = Payment.builder()
            .amount(paymentRequest.getAmount())
            .paymentMethod(paymentRequest.getPaymentMethod())
            .paymentDate(LocalDateTime.now())
            .booking(BookingDto.toEntity(booking))
            .isSuccessful(true)
            .build();

        booking.setPaid(true);
        bookingService.update(booking);

        Payment paymentSaved = paymentRepository.save(payment);
        return PaymentDto.toDto(paymentSaved);
    }
}

