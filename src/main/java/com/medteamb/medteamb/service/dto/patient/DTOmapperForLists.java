package com.medteamb.medteamb.service.dto.patient;

import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.DTOmapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DTOmapperForLists {

    public ResponseForLists<PatientResponseDTO> mapPatientsToDTOList(Iterable<Patient> list, DTOmapper mapper){
        List<PatientResponseDTO> dtoList = new ArrayList<>();
        while (list.iterator().hasNext()){
            dtoList.add(mapper.mapFromPatientToResponse(list.iterator().next()));
        }
        return new ResponseForLists<>(dtoList);
    }
}
