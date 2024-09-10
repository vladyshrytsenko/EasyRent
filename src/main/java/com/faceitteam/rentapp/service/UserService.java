package com.faceitteam.rentapp.service;

import com.faceitteam.rentapp.model.dto.UserDto;
import com.faceitteam.rentapp.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto getById(Long id);
    List<UserDto> findAll();
    UserDto findByRole(Role role);
    UserDto getByUsername(String username);
    UserDto register(UserDto user);
    UserDto update(Long id, UserDto user);
    void delete(Long id);
}

