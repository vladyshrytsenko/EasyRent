package com.faceitteam.rentapp.model.dto;


import com.faceitteam.rentapp.model.entity.Booking;
import com.faceitteam.rentapp.model.enums.BookingStatus;
import com.faceitteam.rentapp.model.enums.RentalType;
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
public class BookingDto {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private OfficeDto office;
    private UserDto user;
    private BookingStatus status;
    private RentalType rentalType;
    private BigDecimal totalPrice;
    private boolean isPaid;

    public static BookingDto toDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        return BookingDto.builder()
            .id(booking.getId())
            .startDate(booking.getStartDate())
            .endDate(booking.getEndDate())
            .status(booking.getStatus())
            .office(OfficeDto.toDto(booking.getOffice()))
            .user(UserDto.toDto(booking.getUser()))
            .build();
    }

    public static List<BookingDto> toDtoList(List<Booking> bookingList) {
        if (bookingList == null || bookingList.isEmpty()) {
            return List.of();
        }

        return bookingList.stream()
            .map(BookingDto::toDto)
            .collect(Collectors.toList());
    }

    public static Booking toEntity(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }

        return Booking.builder()
            .id(bookingDto.getId())
            .startDate(bookingDto.getStartDate())
            .endDate(bookingDto.getEndDate())
            .office(OfficeDto.toEntity(bookingDto.getOffice()))
            .user(UserDto.toEntity(bookingDto.getUser()))
            .status(bookingDto.getStatus())
            .rentalType(bookingDto.getRentalType())
            .totalPrice(bookingDto.getTotalPrice())
            .isPaid(bookingDto.isPaid())
            .build();
    }

    public static List<Booking> toEntityList(List<BookingDto> bookingDtoList) {
        if (bookingDtoList == null || bookingDtoList.isEmpty()) {
            return List.of();
        }

        return bookingDtoList.stream()
            .map(BookingDto::toEntity)
            .collect(Collectors.toList());
    }
}

