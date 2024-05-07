package com.medteamb.medteamb.service.ResponseHandler;


import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import com.medteamb.medteamb.service.dto.patient.PatientResponseDTO;


public class Response<T> extends BaseResponse{

    T body;

    public Response(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
