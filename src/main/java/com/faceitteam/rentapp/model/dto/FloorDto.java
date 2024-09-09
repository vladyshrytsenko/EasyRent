package com.faceitteam.rentapp.model.dto;

import com.faceitteam.rentapp.model.entity.Floor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class FloorDto {

    private Long id;
    private String svgPath;
    private List<OfficeDto> offices;

    public static FloorDto toDto(Floor floor) {
        if (floor == null) {
            return null;
        }

        return FloorDto.builder()
            .id(floor.getId())
            .svgPath(floor.getSvgPath())
            .offices(OfficeDto.toDtoList(floor.getOffices()))
            .build();
    }

    public static List<FloorDto> toDtoList(List<Floor> floors) {
        if (floors == null || floors.isEmpty()) {
            return List.of();
        }

        return floors.stream()
            .map(FloorDto::toDto)
            .collect(Collectors.toList());
    }

    public static Floor toEntity(FloorDto floorDto) {
        if (floorDto == null) {
            return null;
        }

        return Floor.builder()
            .id(floorDto.getId())
            .svgPath(floorDto.getSvgPath())
            .offices(OfficeDto.toEntityList(floorDto.getOffices()))
            .build();
    }

    public static List<Floor> toEntityList(List<FloorDto> floorDtoList) {
        if (floorDtoList == null || floorDtoList.isEmpty()) {
            return List.of();
        }

        return floorDtoList.stream()
            .map(FloorDto::toEntity)
            .collect(Collectors.toList());
    }
}
