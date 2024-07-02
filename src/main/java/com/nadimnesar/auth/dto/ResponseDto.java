package com.nadimnesar.auth.dto;

import com.nadimnesar.auth.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String jwt;
    private UserRole userRole;
}