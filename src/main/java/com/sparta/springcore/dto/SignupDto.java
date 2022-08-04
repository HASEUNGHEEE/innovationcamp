package com.sparta.springcore.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupDto {
    private String username;
    private String nickname;
    private String password;
    private String passwordCheck;
}