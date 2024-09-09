package com.faceitteam.rentapp.model.dto;

import com.faceitteam.rentapp.model.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class NotificationDto {

    private Long id;
    private String message;
    private LocalDateTime sentAt;
    private UserDto manager;
    private BookingDto booking;

    public static NotificationDto toDto(Notification notification) {
        if (notification == null) {
            return null;
        }

        return NotificationDto.builder()
            .id(notification.getId())
            .message(notification.getMessage())
            .sentAt(notification.getSentAt())
            .manager(UserDto.toDto(notification.getManager()))
            .booking(BookingDto.toDto(notification.getBooking()))
            .build();
    }

    public static List<NotificationDto> toDtoList(List<Notification> notifications) {
        if (notifications == null || notifications.isEmpty()) {
            return List.of();
        }

        return notifications.stream()
            .map(NotificationDto::toDto)
            .collect(Collectors.toList());
    }

    public static Notification toEntity(NotificationDto notificationDto) {
        if (notificationDto == null) {
            return null;
        }

        return Notification.builder()
            .id(notificationDto.getId())
            .message(notificationDto.getMessage())
            .sentAt(notificationDto.getSentAt())
            .manager(UserDto.toEntity(notificationDto.getManager()))
            .booking(BookingDto.toEntity(notificationDto.getBooking()))
            .build();
    }

    public static List<Notification> toEntityList(List<NotificationDto> notificationDtos) {
        if (notificationDtos == null || notificationDtos.isEmpty()) {
            return List.of();
        }

        return notificationDtos.stream()
            .map(NotificationDto::toEntity)
            .collect(Collectors.toList());
    }
}
