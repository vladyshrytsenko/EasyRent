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
import java.util.ArrayList;
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
    public FloorDto create(FloorDto floorDto, MultipartFile svgFile, List<MultipartFile> photosFile) {
        List<Office> offices = OfficeDto.toEntityList(floorDto.getOffices());
        String svgStr = null;
        List<String> photosAsBase64 = null;
        if (svgFile != null) {
            try {
                svgStr = convertMultipartFilesToBase64(List.of(svgFile)).get(0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (photosFile != null) {
            try {
                photosAsBase64 = convertMultipartFilesToBase64(photosFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Floor floor = Floor.builder()
            .svgPath(svgStr)
            .number(floorDto.getNumber())
            .offices(offices)
            .photos(photosAsBase64)
            .build();

        floor.setAvailable(true);

        Floor saved = floorRepository.save(floor);
        return FloorDto.toDto(saved);
    }

    @Override
    public FloorDto updateById(Long id, FloorDto floorDto, List<MultipartFile> photosFile) {
        Floor floorById = floorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Floor not found with id: " + id));

        List<String> photosAsBase64 = null;

        if (floorDto.getSvgPath() != null) {
            floorById.setSvgPath(floorDto.getSvgPath());
        }
        if (floorDto.getOffices() != null) {
            List<Office> offices = OfficeDto.toEntityList(floorDto.getOffices());
            floorById.setOffices(offices);
        }
        if (photosFile != null) {
            try {
                photosAsBase64 = convertMultipartFilesToBase64(photosFile);
                floorById.setPhotos(photosAsBase64);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Floor updated = floorRepository.save(floorById);
        return FloorDto.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        floorRepository.deleteById(id);
    }

    private static List<String> convertMultipartFilesToBase64(List<MultipartFile> files) throws IOException {
        List<String> base64Files = new ArrayList<>();

        for (MultipartFile file : files) {
            byte[] fileContent = file.getBytes();
            String base64String = Base64.encodeBase64String(fileContent);
            base64Files.add(base64String);
        }

        return base64Files;
    }

}
