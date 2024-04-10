package com.medteamb.medteamb.service.ResponseHandler;

import org.springframework.http.HttpStatus;


public class GenericResponse<T>  {

    private HttpStatus httpStatus;

    private String message;

    private T body;

   public GenericResponse(){}


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
