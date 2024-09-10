package com.faceitteam.rentapp.service.impl;

import com.faceitteam.rentapp.exception.ResourceNotFoundException;
import com.faceitteam.rentapp.model.dto.UserDto;
import com.faceitteam.rentapp.model.entity.User;
import com.faceitteam.rentapp.model.enums.Role;
import com.faceitteam.rentapp.repository.UserRepository;
import com.faceitteam.rentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return UserDto.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {

        List<User> userList = userRepository.findAll();
        return UserDto.toDtoList(userList);
    }

    @Override
    public UserDto findByRole(Role role) {

        User userByRole = userRepository.findByRole(role).orElse(null);
        return userByRole != null ? UserDto.toDto(userByRole) : null;
    }

    @Override
    public UserDto getByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        return UserDto.toDto(user);
    }

    @Override
    public UserDto register(UserDto userRequestDTO) {
        User user = User.builder()
            .username(userRequestDTO.getUsername())
            .password(userRequestDTO.getPassword()) // should be encrypted
            .email(userRequestDTO.getEmail())
            .role(userRequestDTO.getRole())
            .build();

        User saved = userRepository.save(user);
        return UserDto.toDto(saved);
    }

    @Override
    public UserDto update(Long id, UserDto userRequest) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());

        User updated = userRepository.save(user);
        return UserDto.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

