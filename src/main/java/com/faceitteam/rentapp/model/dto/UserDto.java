package com.faceitteam.rentapp.model.dto;

import com.faceitteam.rentapp.model.entity.User;
import com.faceitteam.rentapp.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    }

    public static List<UserDto> toDtoList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return List.of();
        }

        return users.stream()
            .map(UserDto::toDto)
            .collect(Collectors.toList());
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
            .id(userDto.getId())
            .username(userDto.getUsername())
            .password(userDto.getPassword())
            .email(userDto.getEmail())
            .role(userDto.getRole())
            .build();
    }

    public static List<User> toEntityList(List<UserDto> userDtos) {
        if (userDtos == null || userDtos.isEmpty()) {
            return List.of();
        }

        return userDtos.stream()
            .map(UserDto::toEntity)
            .collect(Collectors.toList());
    }
}
