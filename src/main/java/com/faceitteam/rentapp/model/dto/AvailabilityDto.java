package com.faceitteam.rentapp.model.dto;

import com.faceitteam.rentapp.model.entity.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class AvailabilityDto {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isBlocked;
    private OfficeDto office;

    public static AvailabilityDto toDto(Availability availability) {
        if (availability == null) {
            return null;
        }

        return AvailabilityDto.builder()
            .id(availability.getId())
            .startDate(availability.getStartDate())
            .endDate(availability.getEndDate())
            .isBlocked(availability.isBlocked())
            .office(OfficeDto.toDto(availability.getOffice()))
            .build();
    }

    public static List<AvailabilityDto> toDtoList(List<Availability> availabilities) {
        if (availabilities == null || availabilities.isEmpty()) {
            return List.of();
        }

        return availabilities.stream()
            .map(AvailabilityDto::toDto)
            .collect(Collectors.toList());
    }

    public static Availability toEntity(AvailabilityDto availabilityDto) {
        if (availabilityDto == null) {
            return null;
        }

        return Availability.builder()
            .id(availabilityDto.getId())
            .startDate(availabilityDto.getStartDate())
            .endDate(availabilityDto.getEndDate())
            .isBlocked(availabilityDto.isBlocked())
            .office(OfficeDto.toEntity(availabilityDto.getOffice()))
            .build();
    }

    public static List<Availability> toEntityList(List<AvailabilityDto> availabilityDtoList) {
        if (availabilityDtoList == null || availabilityDtoList.isEmpty()) {
            return List.of();
        }

        return availabilityDtoList.stream()
            .map(AvailabilityDto::toEntity)
            .collect(Collectors.toList());
    }
}
