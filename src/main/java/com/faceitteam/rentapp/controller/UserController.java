package com.faceitteam.rentapp.controller;

import com.faceitteam.rentapp.model.dto.UserDto;
import com.faceitteam.rentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {

        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

//    @GetMapping("/by-username/{username}")
//    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
//        UserDto userDto = userService.getByUsername(username);
//        return ResponseEntity.ok(userDto);
//    }
//
//    @GetMapping("/by-role/{role}")
//    public ResponseEntity<UserDto> getUserByRole(@PathVariable Role role) {
//        UserDto userDto = userService.findByRole(role);
//        if (userDto != null) {
//            return ResponseEntity.ok(userDto);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userRequestDto) {

        UserDto savedUser = userService.register(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
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

