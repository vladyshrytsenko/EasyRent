package com.faceitteam.rentapp.model.dto;

import com.faceitteam.rentapp.model.entity.Office;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class OfficeDto {

    private Long id;
    private String name;
    private String description;
    private int capacity;
    private List<String> photos;
    private BigDecimal hourlyPrice;
    private BigDecimal dailyPrice;
    private BigDecimal monthlyPrice;
    private BigDecimal longTermPrice;
    private FloorDto floor;

    private List<BookingDto> bookings;
    private List<AvailabilityDto> availabilities;

    public static OfficeDto toDto(Office office) {
        if (office == null) {
            return null;
        }

        return OfficeDto.builder()
            .id(office.getId())
            .name(office.getName())
            .description(office.getDescription())
            .capacity(office.getCapacity())
            .hourlyPrice(office.getHourlyPrice())
            .dailyPrice(office.getDailyPrice())
            .monthlyPrice(office.getMonthlyPrice())
            .longTermPrice(office.getLongTermPrice())
            .bookings(BookingDto.toDtoList(office.getBookings()))
            .availabilities(AvailabilityDto.toDtoList(office.getAvailabilities()))
            .floor(FloorDto.toDto(office.getFloor()))
            .build();
    }

    public static List<OfficeDto> toDtoList(List<Office> offices) {
        if (offices == null || offices.isEmpty()) {
            return List.of();
        }

        return offices.stream()
            .map(OfficeDto::toDto)
            .collect(Collectors.toList());
    }

    public static Office toEntity(OfficeDto officeDto) {
        if (officeDto == null) {
            return null;
        }

        return Office.builder()
            .id(officeDto.getId())
            .name(officeDto.getName())
            .description(officeDto.getDescription())
            .capacity(officeDto.getCapacity())
            .hourlyPrice(officeDto.getHourlyPrice())
            .dailyPrice(officeDto.getDailyPrice())
            .monthlyPrice(officeDto.getMonthlyPrice())
            .longTermPrice(officeDto.getLongTermPrice())
            .bookings(BookingDto.toEntityList(officeDto.getBookings()))
            .availabilities(AvailabilityDto.toEntityList(officeDto.getAvailabilities()))
            .floor(FloorDto.toEntity(officeDto.getFloor()))
            .build();
    }

    public static List<Office> toEntityList(List<OfficeDto> offices) {
        if (offices == null || offices.isEmpty()) {
            return List.of();
        }

        return offices.stream()
            .map(OfficeDto::toEntity)
            .collect(Collectors.toList());
    }
}
