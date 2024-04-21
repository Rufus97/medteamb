package com.medteamb.medteamb.service.ResponseHandler.PatientResponse;

import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import com.medteamb.medteamb.service.dto.patient.PatientDTO;

import java.util.ArrayList;
import java.util.List;

public class PatientResponseIterables extends BaseResponse {

   List<PatientDTO> body = new ArrayList<>();

    public PatientResponseIterables(){}

    public PatientResponseIterables(List<PatientDTO> body){
     this.body = body;
    }

    public List<PatientDTO> getBody() {
        return body;
    }
    public void setBody(List<PatientDTO> body) {
        this.body = body;
    }


}
