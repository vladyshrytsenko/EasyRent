package com.faceitteam.rentapp.service;

import com.faceitteam.rentapp.model.dto.FloorDto;

import java.util.List;

public interface FloorService {

    FloorDto getById(Long id);
    List<FloorDto> findAll();
    FloorDto create(FloorDto floorDto);
    FloorDto updateById(Long id, FloorDto floorDto);
    void deleteById(Long id);
}
