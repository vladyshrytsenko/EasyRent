package com.faceitteam.rentapp.service.impl;

import com.faceitteam.rentapp.exception.ResourceNotFoundException;
import com.faceitteam.rentapp.model.dto.FloorDto;
import com.faceitteam.rentapp.model.dto.OfficeDto;
import com.faceitteam.rentapp.model.entity.Floor;
import com.faceitteam.rentapp.model.entity.Office;
import com.faceitteam.rentapp.repository.FloorRepository;
import com.faceitteam.rentapp.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    @Override
    public FloorDto getById(Long id) {
        Floor floor = floorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Floor not found with id: " + id));

        return FloorDto.toDto(floor);
    }

    @Override
    public List<FloorDto> findAll() {
        List<Floor> floors = floorRepository.findAll();
        return FloorDto.toDtoList(floors);
    }

    @Override
    public FloorDto create(FloorDto floorDto, MultipartFile file) {
        List<Office> offices = OfficeDto.toEntityList(floorDto.getOffices());
        String svgStr = null;
        if (file != null) {
            try {
                svgStr = convertMultipartFileToBase64(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Floor floor = Floor.builder()
            .svgPath(svgStr)
            .offices(offices)
            .photos(floorDto.getPhotos())
            .build();

        floor.setAvailable(true);

        Floor saved = floorRepository.save(floor);
        return FloorDto.toDto(saved);
    }

    @Override
    public FloorDto updateById(Long id, FloorDto floorDto) {
        Floor floorById = floorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Floor not found with id: " + id));

        if (floorDto.getSvgPath() != null) {
            floorById.setSvgPath(floorDto.getSvgPath());
        }
        if (floorDto.getOffices() != null) {
            List<Office> offices = OfficeDto.toEntityList(floorDto.getOffices());
            floorById.setOffices(offices);
        }
        if (floorDto.getPhotos() != null) {
            floorById.setPhotos(floorDto.getPhotos());
        }

        Floor updated = floorRepository.save(floorById);
        return FloorDto.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        floorRepository.deleteById(id);
    }

    private static String convertMultipartFileToBase64(MultipartFile file) throws IOException {
        byte[] fileContent = file.getBytes();
        return Base64.encodeBase64String(fileContent);
    }
}
