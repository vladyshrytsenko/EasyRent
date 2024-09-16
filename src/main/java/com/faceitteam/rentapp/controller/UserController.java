package com.faceitteam.rentapp.controller;

import com.faceitteam.rentapp.model.AuthenticationResponse;
import com.faceitteam.rentapp.model.dto.UserDto;
import com.faceitteam.rentapp.service.UserService;
import com.faceitteam.rentapp.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {

        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserDto userRequestDto) {

        AuthenticationResponse registered = authenticationService.register(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registered);
    }

    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserDto userRequestDto) {

        AuthenticationResponse authenticated = authenticationService.authenticate(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(authenticated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
        @PathVariable Long id,
        @RequestBody UserDto userRequestDto) {

        UserDto updatedUser = userService.update(id, userRequestDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

