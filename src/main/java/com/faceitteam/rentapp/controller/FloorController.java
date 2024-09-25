package com.faceitteam.rentapp.controller;

import com.faceitteam.rentapp.model.dto.FloorDto;
import com.faceitteam.rentapp.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/floors")
@RequiredArgsConstructor
public class FloorController {

    private final FloorService floorService;

    @GetMapping("/{id}")
    public ResponseEntity<FloorDto> getById(@PathVariable Long id) {

        FloorDto floorDto = floorService.getById(id);
        return ResponseEntity.ok(floorDto);
    }

    @GetMapping
    public ResponseEntity<List<FloorDto>> getAll() {

        List<FloorDto> floors = floorService.findAll();
        return ResponseEntity.ok(floors);
    }

    @PostMapping
    public ResponseEntity<FloorDto> create(
        @RequestPart(value = "floor") FloorDto floorRequestDto,
        @RequestPart(value = "svg", required = false) MultipartFile svg,
        @RequestPart(value = "photos", required = false) List<MultipartFile> photos
        ) {

        FloorDto savedFloor = floorService.create(floorRequestDto, svg, photos);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFloor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FloorDto> updateById(
        @PathVariable Long id,
        @RequestPart(value = "floor") FloorDto floorRequestDto,
        @RequestPart(value = "photos", required = false) List<MultipartFile> photos) {

        FloorDto updatedFloor = floorService.updateById(id, floorRequestDto, photos);
        return ResponseEntity.ok(updatedFloor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        floorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
