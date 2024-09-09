package com.faceitteam.rentapp.service;

import com.faceitteam.rentapp.model.dto.BookingDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    BookingDto create(BookingDto bookingDto);
//    BookingDto confirm(Long bookingId);
    BookingDto update(BookingDto bookingDto);
    BookingDto cancel(Long bookingId);
    boolean isOfficeAvailable(Long officeId, LocalDateTime startDate, LocalDateTime endDate);
    List<BookingDto> findAll();
    BookingDto getById(Long bookingId);
}

