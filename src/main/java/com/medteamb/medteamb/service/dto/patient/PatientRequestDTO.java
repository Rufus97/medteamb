package com.medteamb.medteamb.service.dto.patient;

public class PatientRequestDTO {
    private Integer patientId;
    private String patientName;
    private String patientSurname;
    private String taxCode;
    private String patientPhoneNumber;
    private String patientEmail;

    public PatientRequestDTO() {}

    private PatientRequestDTO(Builder builder) {
        this.patientId = builder.patientId;
        this.patientName = builder.patientName;
        this.patientSurname = builder.patientSurname;
        this.taxCode = builder.taxCode;
        this.patientPhoneNumber = builder.patientPhoneNumber;
        this.patientEmail = builder.patientEmail;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer patientId;
        private String patientName;
        private String patientSurname;
        private String taxCode;
        private String patientPhoneNumber;
        private String patientEmail;

        private Builder() {}

        public Builder withPatientId(Integer patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder withPatientName(String patientName) {
            this.patientName = patientName;
            return this;
        }

        public Builder withPatientSurname(String patientSurname) {
            this.patientSurname = patientSurname;
            return this;
        }

        public Builder withTaxCode(String taxCode) {
            this.taxCode = taxCode;
            return this;
        }

        public Builder withPatientPhoneNumber(String patientPhoneNumber) {
            this.patientPhoneNumber = patientPhoneNumber;
            return this;
        }

        public Builder withPatientEmail(String patientEmail) {
            this.patientEmail = patientEmail;
            return this;
        }

        public PatientRequestDTO build() {
            return new PatientRequestDTO(this);
        }


    }
}
