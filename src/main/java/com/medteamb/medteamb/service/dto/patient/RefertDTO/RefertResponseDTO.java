package com.medteamb.medteamb.service.dto.patient.RefertDTO;

import com.medteamb.medteamb.model.Patient.Patient;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class RefertResponseDTO {

    private Long id;
    private Long patient_id;
    private LocalDateTime emissionDate;

    private String diagnosis;

    public RefertResponseDTO(Long id, Long patient_id, LocalDateTime emissionDate, String diagnosis) {
        this.id = id;
        this.patient_id = patient_id;
        this.emissionDate = emissionDate;
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public LocalDateTime getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDateTime emissionDate) {
        this.emissionDate = emissionDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
