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
    private boolean isAvailable;
    private List<OfficeDto> offices;
    private List<String> photos;

    public static FloorDto toDto(Floor floor) {
        if (floor == null) {
            return null;
        }

        return FloorDto.builder()
            .id(floor.getId())
            .svgPath(floor.getSvgPath())
            .isAvailable(floor.isAvailable())
            .offices(OfficeDto.toDtoList(floor.getOffices()))
            .photos(floor.getPhotos())
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
            .isAvailable(floorDto.isAvailable())
            .offices(OfficeDto.toEntityList(floorDto.getOffices()))
            .photos(floorDto.getPhotos())
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
