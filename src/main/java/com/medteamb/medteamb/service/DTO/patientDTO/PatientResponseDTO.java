package com.medteamb.medteamb.service.DTO.patientDTO;

import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientResponseDTO {
    @Autowired
    private PatientRepository repo;

    private String patientName;
    private String patientSurname;
    private String taxCode;

    public PatientResponseDTO(){}

    public PatientResponseDTO(Patient patient){
        this.patientName = patient.getPatientName();
        this.patientSurname = patient.getPatientSurname();
        this.taxCode = patient.getTaxCode();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
}
