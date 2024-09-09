package com.faceitteam.rentapp.model.dto;

import com.faceitteam.rentapp.model.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Long id;
    private BigDecimal amount;
    private BookingDto booking;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private boolean isSuccessful;

    public static PaymentDto toDto(Payment payment) {
        if (payment == null) {
            return null;
        }

        return PaymentDto.builder()
            .id(payment.getId())
            .amount(payment.getAmount())
            .booking(BookingDto.toDto(payment.getBooking()))
            .paymentMethod(payment.getPaymentMethod())
            .paymentDate(payment.getPaymentDate())
            .isSuccessful(payment.isSuccessful())
            .build();
    }

    public static List<PaymentDto> toDtoList(List<Payment> payments) {
        if (payments == null || payments.isEmpty()) {
            return List.of();
        }

        return payments.stream()
            .map(PaymentDto::toDto)
            .collect(Collectors.toList());
    }

    public static Payment toEntity(PaymentDto paymentDto) {
        if (paymentDto == null) {
            return null;
        }

        return Payment.builder()
            .id(paymentDto.getId())
            .amount(paymentDto.getAmount())
            .booking(BookingDto.toEntity(paymentDto.getBooking()))
            .paymentMethod(paymentDto.getPaymentMethod())
            .paymentDate(paymentDto.getPaymentDate())
            .isSuccessful(paymentDto.isSuccessful())
            .build();
    }

    public static List<Payment> toEntityList(List<PaymentDto> paymentDtos) {
        if (paymentDtos == null || paymentDtos.isEmpty()) {
            return List.of();
        }

        return paymentDtos.stream()
            .map(PaymentDto::toEntity)
            .collect(Collectors.toList());
    }
}
