package com.medteamb.medteamb.service.ResponseHandler;

import org.springframework.http.HttpStatus;

public class BaseResponse{

    private HttpStatus httpStatus = HttpStatus.OK;
    private String message = "no message";

    private Throwable cause;

    public BaseResponse(){}

    public BaseResponse(String message){
        this.message = message;
    }
    public BaseResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BaseResponse(HttpStatus httpStatus, String message, Throwable cause) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.cause = cause;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
