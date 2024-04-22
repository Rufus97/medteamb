package com.medteamb.medteamb.service.ResponseHandler.PatientResponse;


import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import com.medteamb.medteamb.service.dto.patient.PatientResponseDTO;


import java.util.ArrayList;
import java.util.List;

public class PatientResponseIterables extends BaseResponse {

   List<PatientResponseDTO> body = new ArrayList<>();

    public PatientResponseIterables(){}

    public PatientResponseIterables(List<PatientResponseDTO> body){
     this.body = body;
    }

    public List<PatientResponseDTO> getBody() {
        return body;
    }
    public void setBody(List<PatientResponseDTO> body) {
        this.body = body;
    }


}
