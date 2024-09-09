package com.faceitteam.rentapp.repository;

import com.faceitteam.rentapp.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByOffice_IdAndStartDateBetween(Long officeId, LocalDateTime startDate, LocalDateTime endDate);
}
