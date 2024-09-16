package com.faceitteam.rentapp.service.auth;

import com.faceitteam.rentapp.model.AuthenticationResponse;
import com.faceitteam.rentapp.model.dto.UserDto;
import com.faceitteam.rentapp.model.entity.User;
import com.faceitteam.rentapp.model.enums.Role;
import com.faceitteam.rentapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDto userRequestDTO) {
        User user = User.builder()
            .username(userRequestDTO.getUsername())
            .password(passwordEncoder.encode(userRequestDTO.getPassword()))
            .email(userRequestDTO.getEmail())
            .role(Role.USER)
            .build();

        User saved = userRepository.save(user);

        return getAuthenticationResponse(saved);
    }

    public AuthenticationResponse authenticate(UserDto user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            user.getEmail(),
            user.getPassword())
        );

        User userByEmail = userRepository.findByEmail(user.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return getAuthenticationResponse(userByEmail);
    }

    private AuthenticationResponse getAuthenticationResponse(User saved) {
        String generatedToken = jwtService.generateToken(saved);

        return AuthenticationResponse.builder()
            .token(generatedToken)
            .build();
    }
}
