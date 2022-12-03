package com.sparta.springcore.common.exception;

import com.sparta.springcore.common.Constants;
import org.springframework.http.HttpStatus;

public class UserException extends Exception{
    private static final long serialVersionUID = 827419284719293L;

    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public UserException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass(){
        return exceptionClass;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}