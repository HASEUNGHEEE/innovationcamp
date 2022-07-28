package com.example.mypost.domain;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class LoginForm {
    @NotNull
    private String password;

    public LoginForm(String password) {
        this.password = password;
    }
}


