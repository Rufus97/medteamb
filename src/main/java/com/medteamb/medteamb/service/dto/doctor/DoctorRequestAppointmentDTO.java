package com.medteamb.medteamb.service.dto.doctor;

public class DoctorRequestAppointmentDTO {


    private Long patientID;
    private Integer appointmentID;

    private String	medicalService;
    private String location;
    private String taxCode;

    public String getMedicalService() {
        return medicalService;
    }

    public void setMedicalService(String medicalService) {
        this.medicalService = medicalService;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Long getPatientID() {
        return patientID;
    }
    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }
    public Integer getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }
}
