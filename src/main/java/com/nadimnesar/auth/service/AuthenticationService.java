package com.nadimnesar.auth.service;

import com.nadimnesar.auth.dto.ResponseDto;
import com.nadimnesar.auth.dto.UserDto;
import com.nadimnesar.auth.enums.UserRole;
import com.nadimnesar.auth.model.User;
import com.nadimnesar.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                                 JwtService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public boolean invalidUserDto(UserDto userDto) {
        return (userDto.getPassword() == null) || (userDto.getUsername() == null);
    }

    public ResponseEntity<?> register(UserDto userDto, UserRole role) {
        if (invalidUserDto(userDto)) return new ResponseEntity<>(
                "Please provide both username and password.", HttpStatus.BAD_REQUEST);

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(role);

        try {
            String token = jwtService.generateToken(user);
            try {
                userRepository.save(user);
            } catch (Exception e) {
                return new ResponseEntity<>("Username already exists.", HttpStatus.CONFLICT);
            }
            ResponseDto responseDto = new ResponseDto(token, role);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while generating the token.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> login(UserDto userDto) {
        if (invalidUserDto(userDto)) return new ResponseEntity<>(
                "Please provide both username and password.", HttpStatus.BAD_REQUEST);

        User user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) return new ResponseEntity<>("User not found.", HttpStatus.UNAUTHORIZED);

        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDto.getUsername(), userDto.getPassword());
            authenticationManager.authenticate(authToken);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect password.", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtService.generateToken(user);
        ResponseDto responseDto = new ResponseDto(token, user.getRole());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}