package com.medteamb.medteamb.service.dto.patient.RefertDTO;

import com.medteamb.medteamb.model.Patient.Patient;

public class RefertRequestDTO {

    private Patient patient;
    private String diagnosis;

    public RefertRequestDTO(Patient patient, String diagnosis) {
        this.patient = patient;
        this.diagnosis = diagnosis;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
