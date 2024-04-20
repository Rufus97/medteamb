package com.medteamb.medteamb.service.dto.secretary;

public class SecretaryRequestDTO {

    private String secretaryName;
    private String secretarySurname;
    private String secretaryPhoneNumber;
    private String secretaryEmail;

    public SecretaryRequestDTO() {}
    private SecretaryRequestDTO(Builder builder) {
        this.secretaryName = builder.secretaryName;
        this.secretarySurname = builder.secretarySurname;
        this.secretaryPhoneNumber = builder.secretaryPhoneNumber;
        this.secretaryEmail = builder.secretaryEmail;
    }


    public String getSecretaryName() {
        return secretaryName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName;
    }

    public String getSecretarySurname() {
        return secretarySurname;
    }

    public void setSecretarySurname(String secretarySurname) {
        this.secretarySurname = secretarySurname;
    }

    public String getSecretaryPhoneNumber() {
        return secretaryPhoneNumber;
    }

    public void setSecretaryPhoneNumber(String secretaryPhoneNumber) {
        this.secretaryPhoneNumber = secretaryPhoneNumber;
    }

    public String getSecretaryEmail() {
        return secretaryEmail;
    }

    public void setSecretaryEmail(String secretaryEmail) {
        this.secretaryEmail = secretaryEmail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String secretaryName;
        private String secretarySurname;
        private String secretaryPhoneNumber;
        private String secretaryEmail;

        private Builder() {
        }





        //poi servira' per lo user
        public Builder withSecretaryName(String secretaryName) {
            this.secretaryName = secretaryName;
            return this;
        }

        public Builder withSecretarySurname(String secretarySurname) {
            this.secretarySurname = secretarySurname;
            return this;
        }

        public Builder withSecretaryPhoneNumber(String secretaryPhoneNumber) {
            this.secretaryPhoneNumber = secretaryPhoneNumber;
            return this;
        }

        public Builder withSecretaryEmail(String secretaryEmail) {
            this.secretaryEmail = secretaryEmail;
            return this;
        }

        public SecretaryRequestDTO build() {
            return new SecretaryRequestDTO(this);
        }

    }
}
