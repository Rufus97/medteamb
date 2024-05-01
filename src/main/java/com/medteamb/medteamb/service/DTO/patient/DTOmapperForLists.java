package com.medteamb.medteamb.service.dto.patient;

import com.medteamb.medteamb.model.Patient.Patient;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DTOmapperForLists {

    public ResponseForLists mapPatientsToDTOList(Iterable<Patient> list, DTOmapper mapper){
        List<PatientResponseDTO> dtoList = new ArrayList<>();
        while (list.iterator().hasNext()){
            dtoList.add(mapper.mapFromPatientToResponse(list.iterator().next()));
        }
        return new ResponseForLists(dtoList);
    }
}
