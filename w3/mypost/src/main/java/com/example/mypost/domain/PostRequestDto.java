package com.example.mypost.domain;

import lombok.Getter;
@Getter
public class PostRequestDto {
    private String username;
    private String password;
    private String contents;
    private String title;
}
