package com.faceitteam.rentapp.controller;

import com.faceitteam.rentapp.model.dto.AvailabilityDto;
import com.faceitteam.rentapp.model.dto.OfficeDto;
import com.faceitteam.rentapp.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @PostMapping
    public ResponseEntity<OfficeDto> createOffice(@RequestBody OfficeDto officeDto) {

        OfficeDto createdOffice = officeService.create(officeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffice);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<OfficeDto> updateOfficeAvailability(
        @PathVariable Long id,
        @RequestBody AvailabilityDto availabilityDto) {

        OfficeDto updatedOffice = officeService.updateAvailability(id, availabilityDto);
        return ResponseEntity.ok(updatedOffice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffice(@PathVariable Long id) {

        officeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficeDto> getOfficeById(@PathVariable Long id) {

        OfficeDto officeDto = officeService.getById(id);
        return ResponseEntity.ok(officeDto);
    }

    @GetMapping
    public ResponseEntity<List<OfficeDto>> getAllOffices() {

        List<OfficeDto> offices = officeService.findAll();
        return ResponseEntity.ok(offices);
    }
}

