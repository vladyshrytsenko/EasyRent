package com.faceitteam.rentapp.service;

import com.faceitteam.rentapp.model.dto.AvailabilityDto;
import com.faceitteam.rentapp.model.dto.OfficeDto;
import com.faceitteam.rentapp.model.entity.Office;

import java.util.List;

public interface OfficeService {

    OfficeDto create(OfficeDto office);
    OfficeDto updateAvailability(Long id, AvailabilityDto availabilityDto);
    void delete(Long officeId);
    OfficeDto getById(Long officeId);

    List<OfficeDto> findAll();
}

