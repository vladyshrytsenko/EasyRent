package com.faceitteam.rentapp.config;

import com.faceitteam.rentapp.model.entity.User;
import com.faceitteam.rentapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User currentUser = null;
                if (!username.contains("@")) {
                    Optional<User> byUsername = userRepository.findByUsername(username);
                    if (byUsername.isPresent()) {
                        currentUser = userRepository.findByEmail(byUsername.get().getEmail())
                            .orElseThrow(() -> new UsernameNotFoundException("User not found."));
                    }
                } else {
                    currentUser = userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found."));
                }
                return currentUser;
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getUserDetailsService());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
