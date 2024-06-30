package com.nadimnesar.controller;

import com.nadimnesar.dto.UserDto;
import com.nadimnesar.enums.UserRole;
import com.nadimnesar.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    AuthenticationService authenticationService;
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody UserDto userDto) {
        return authenticationService.login(userDto);
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