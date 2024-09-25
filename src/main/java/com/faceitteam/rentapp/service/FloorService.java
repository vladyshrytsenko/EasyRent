package com.faceitteam.rentapp.service;

import com.faceitteam.rentapp.model.dto.FloorDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FloorService {

    FloorDto getById(Long id);
    List<FloorDto> findAll();
    FloorDto create(FloorDto floorDto, MultipartFile file, List<MultipartFile> photos);
    FloorDto updateById(Long id, FloorDto floorDto, List<MultipartFile> photosFile);
    void deleteById(Long id);
}
