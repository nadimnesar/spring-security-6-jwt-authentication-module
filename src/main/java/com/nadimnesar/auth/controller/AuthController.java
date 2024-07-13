package com.nadimnesar.auth.controller;

import com.nadimnesar.auth.dto.RefreshDto;
import com.nadimnesar.auth.dto.UserDto;
import com.nadimnesar.auth.enums.UserRole;
import com.nadimnesar.auth.service.AuthenticationService;
import com.nadimnesar.auth.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody UserDto userDto) {
        return authenticationService.login(userDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> getJwtToken(@RequestBody RefreshDto refreshDto) {
        return authenticationService.refresh(refreshDto.getRefreshToken());
    }

    @PostMapping("/register")
    public ResponseEntity<?> postRegister(@RequestBody UserDto userDto) {
        return authenticationService.register(userDto, UserRole.USER);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> postAdminRegister(@RequestBody UserDto userDto) {
        return authenticationService.register(userDto, UserRole.ADMIN);
    }
}