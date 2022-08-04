package com.sparta.springcore.validator;

import com.sparta.springcore.common.Constants;
import com.sparta.springcore.common.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class SignupValidator {
    public static void isValidUsername(String username) throws UserException {
        String regexUsername = "^[a-zA-Z0-9]{4,12}$";
        if(!Pattern.matches(regexUsername, username)){
            throw new UserException(Constants.ExceptionClass.SIGNUP_USERNAME, HttpStatus.BAD_REQUEST, "아이디는 최소 4자 이상 12자 이하, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.");
        }
    }

    public static void isValidPassword(String password) throws UserException{
        String regexPassword = "^(?=.*[A-Za-z])(?=.*\\d)[a-z\\d~!@#$%^&*()+|=]{4,32}$";
        if(!Pattern.matches(regexPassword, password)){
            throw new UserException(Constants.ExceptionClass.SIGNUP_PASSWORD, HttpStatus.BAD_REQUEST, "비밀번호는 최소 4자에서 최대 32자 이하, 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.");
        }
    }

    public static void isValidPasswordCheck(String password, String passwordCheck) throws UserException {
        if(!password.equals(passwordCheck)){
            throw new UserException(Constants.ExceptionClass.SIGNUP_PASSWORDCHECK, HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다");
        }
    }
}