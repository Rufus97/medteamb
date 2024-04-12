package com.medteamb.medteamb.service.DTO.patientDTO;

import com.medteamb.medteamb.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDTOList {

    List<PatientDTO> dtoListPatients;
    private static DTOmapper mapper = new DTOmapper();


    public PatientDTOList(){}
    public PatientDTOList(Iterable<Patient> dtoIterablePatient) {
        List<PatientDTO> dtoList = new ArrayList<>();
        while (dtoIterablePatient.iterator().hasNext()){
            dtoList.add(mapper.mapFromPatientToResponse(dtoIterablePatient.iterator().next()));
        }
        this.dtoListPatients = dtoList;
    }

    public List<PatientDTO> getDtoListPatients() {
        return dtoListPatients;
    }

    public void setDtoListPatients(List<PatientDTO> dtoListPatients) {
        this.dtoListPatients = dtoListPatients;
    }

    public static DTOmapper getMapper() {
        return mapper;
    }

}
