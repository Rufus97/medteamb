package com.medteamb.medteamb.service.dto.patient;

import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponseIterables;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ListOfDTOmapper{

    public PatientResponseIterables mapPatientsToDTOList(Iterable<Patient> list, DTOmapper mapper){
        List<PatientDTO> dtoList = new ArrayList<>();
        while (list.iterator().hasNext()){
            dtoList.add(mapper.mapFromPatientToResponse(list.iterator().next()));
        }
        return new PatientResponseIterables(dtoList);
    }
}
