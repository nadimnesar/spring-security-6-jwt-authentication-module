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

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                                 JwtService jwtService, AuthenticationManager authenticationManager,
                                 RefreshTokenService refreshTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
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
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>("Username already exists.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Registration successful.", HttpStatus.CREATED);
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

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.generateRefreshToken(user.getUsername());

        ResponseDto responseDto = new ResponseDto(jwtToken, refreshToken, user.getRole());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    public ResponseEntity<?> refresh(String token) {
        if (refreshTokenService.isValid(token)) {
            String jwtToken = refreshTokenService.getJwtToken(token);
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("JwtToken", jwtToken);
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Refresh token expired.", HttpStatus.UNAUTHORIZED);
    }
}