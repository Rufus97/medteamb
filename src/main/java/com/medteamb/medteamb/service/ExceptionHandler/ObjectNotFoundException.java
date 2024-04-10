package com.medteamb.medteamb.service.ExceptionHandler;

public class ObjectNotFoundException extends NullPointerException{

    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
