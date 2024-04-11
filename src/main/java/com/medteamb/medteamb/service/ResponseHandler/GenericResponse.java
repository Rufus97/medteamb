package com.medteamb.medteamb.service.ResponseHandler;

import org.springframework.http.HttpStatus;


public class GenericResponse<T>  {

    private HttpStatus httpStatus = HttpStatus.OK;

    private String message = "no message";

    private T body;

   public GenericResponse(){}

    public GenericResponse(T body) {
        this.body = body;
    }

    public GenericResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public GenericResponse(HttpStatus httpStatus, String message, T body) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T dto){
        this.body = dto;
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
}
