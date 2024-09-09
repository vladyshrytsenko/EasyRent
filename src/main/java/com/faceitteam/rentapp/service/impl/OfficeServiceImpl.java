package com.faceitteam.rentapp.service.impl;

import com.faceitteam.rentapp.exception.ResourceNotFoundException;
import com.faceitteam.rentapp.model.dto.AvailabilityDto;
import com.faceitteam.rentapp.model.dto.OfficeDto;
import com.faceitteam.rentapp.model.entity.Availability;
import com.faceitteam.rentapp.model.entity.Office;
import com.faceitteam.rentapp.repository.AvailabilityRepository;
import com.faceitteam.rentapp.repository.OfficeRepository;
import com.faceitteam.rentapp.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private final AvailabilityRepository availabilityRepository;

    @Override
    public OfficeDto create(OfficeDto officeDto) {
        Office office = Office.builder()
            .name(officeDto.getName())
            .description(officeDto.getDescription())
            .photos(officeDto.getPhotos())
            .hourlyPrice(officeDto.getHourlyPrice())
            .dailyPrice(officeDto.getDailyPrice())
            .monthlyPrice(officeDto.getMonthlyPrice())
            .build();

        Office saved = officeRepository.save(office);
        return OfficeDto.toDto(saved);
    }

    @Override
    public OfficeDto updateAvailability(Long id, AvailabilityDto availabilityDto) {
        Office office = officeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Office not found with id: " + id));

        Availability availability = Availability.builder()
            .office(office)
            .startDate(availabilityDto.getStartDate())
            .endDate(availabilityDto.getEndDate())
            .isBlocked(availabilityDto.isBlocked())
            .build();

        availabilityRepository.save(availability);

        return OfficeDto.toDto(office);
    }

    @Override
    public void delete(Long id) {
        officeRepository.deleteById(id);
    }

    @Override
    public OfficeDto getById(Long id) {
        Office office = officeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Office not found with id: " + id));

        return OfficeDto.toDto(office);
    }

    @Override
    public List<OfficeDto> findAll() {
        List<Office> offices = officeRepository.findAll();

        return OfficeDto.toDtoList(offices);
    }
}

