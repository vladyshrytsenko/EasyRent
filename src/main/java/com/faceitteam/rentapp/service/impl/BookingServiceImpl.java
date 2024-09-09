package com.faceitteam.rentapp.service.impl;

import com.faceitteam.rentapp.exception.ResourceNotFoundException;
import com.faceitteam.rentapp.model.dto.BookingDto;
import com.faceitteam.rentapp.model.dto.OfficeDto;
import com.faceitteam.rentapp.model.dto.UserDto;
import com.faceitteam.rentapp.model.entity.Booking;
import com.faceitteam.rentapp.model.enums.BookingStatus;
import com.faceitteam.rentapp.repository.BookingRepository;
import com.faceitteam.rentapp.service.BookingService;
import com.faceitteam.rentapp.service.OfficeService;
import com.faceitteam.rentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final OfficeService officeService;
    private final UserService userService;

    @Override
    public BookingDto create(BookingDto bookingDto) {
        OfficeDto office = officeService.getById(bookingDto.getOffice().getId());
        UserDto user = userService.getById(bookingDto.getUser().getId());

        // Checking office availability
        if (!isOfficeAvailable(office.getId(), bookingDto.getStartDate(), bookingDto.getEndDate())) {
            throw new IllegalStateException("Office is not available during this period.");
        }

        Booking booking = Booking.builder()
            .startDate(bookingDto.getStartDate())
            .endDate(bookingDto.getEndDate())
            .office(OfficeDto.toEntity(office))
            .user(UserDto.toEntity(user))
            .rentalType(bookingDto.getRentalType())
            .status(BookingStatus.PENDING)
            .totalPrice(calculatePrice(bookingDto))
            .isPaid(false)
            .build();

        Booking saved = bookingRepository.save(booking);
        return BookingDto.toDto(saved);
    }

    @Override
    public BookingDto update(BookingDto bookingDto) {
        BookingDto existingBooking = this.getById(bookingDto.getId());

        existingBooking.setPaid(bookingDto.isPaid());
        existingBooking.setStatus(bookingDto.getStatus());

        Booking bookingEntity = BookingDto.toEntity(existingBooking);
        Booking updatedEntity = bookingRepository.save(bookingEntity);
        return BookingDto.toDto(updatedEntity);
    }

    @Override
    public boolean isOfficeAvailable(
        Long officeId,
        LocalDateTime startDate,
        LocalDateTime endDate) {

        List<Booking> conflictingBookings = bookingRepository.findByOffice_IdAndStartDateBetween(
            officeId, startDate, endDate
        );
        return conflictingBookings.isEmpty();
    }

    @Override
    public BookingDto cancel(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        booking.setStatus(BookingStatus.CANCELED);

        Booking updated = bookingRepository.save(booking);
        return BookingDto.toDto(updated);
    }

    @Override
    public List<BookingDto> findAll() {
        List<Booking> bookings = bookingRepository.findAll();

        return BookingDto.toDtoList(bookings);
    }

    @Override
    public BookingDto getById(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        return BookingDto.toDto(booking);
    }

    private BigDecimal calculatePrice(BookingDto booking) {
        // fixme: Example of cost calculation logic depending on rental type and period
        return new BigDecimal("0.00");  // Insert actual cost calculation
    }

}

