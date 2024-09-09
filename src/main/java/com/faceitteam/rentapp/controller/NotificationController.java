package com.faceitteam.rentapp.controller;

import com.faceitteam.rentapp.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send/{bookingId}")
    public ResponseEntity<Void> send(@PathVariable Long bookingId) {

        notificationService.sendBookingNotification(bookingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

