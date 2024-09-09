package com.faceitteam.rentapp.service.impl;

import com.faceitteam.rentapp.model.dto.BookingDto;
import com.faceitteam.rentapp.model.dto.UserDto;
import com.faceitteam.rentapp.model.entity.Notification;
import com.faceitteam.rentapp.model.enums.Role;
import com.faceitteam.rentapp.repository.NotificationRepository;
import com.faceitteam.rentapp.service.BookingService;
import com.faceitteam.rentapp.service.NotificationService;
import com.faceitteam.rentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final BookingService bookingService;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendBookingNotification(Long bookingId) {

        BookingDto booking = bookingService.getById(bookingId);
        UserDto manager = userService.findByRole(Role.MANAGER);

        if (manager != null) {
            Notification notification = Notification.builder()
                .message("New booking created for office: " + booking.getOffice().getName())
                .sentAt(LocalDateTime.now())
                .manager(UserDto.toEntity(manager))
                .booking(BookingDto.toEntity(booking))
                .build();

            notificationRepository.save(notification);
        }
    }
}
