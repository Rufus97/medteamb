package com.medteamb.medteamb.service.dto.secretary;

public class SecretaryRequestDTO {

    private Integer secretaryID;
    private String secretaryName;
    private String secretarySurname;
    private String secretaryPhoneNumber;
    private String secretaryEmail;

    public SecretaryRequestDTO() {}
    private SecretaryRequestDTO(Builder builder) {
        this.secretaryID = builder.secretaryID;
        this.secretaryName = builder.secretaryName;
        this.secretarySurname = builder.secretarySurname;
        this.secretaryPhoneNumber = builder.secretaryPhoneNumber;
        this.secretaryEmail = builder.secretaryEmail;
    }

    public Integer getSecretaryID() {
        return secretaryID;
    }

    public void setSecretaryID(Integer secretaryID) {
        this.secretaryID = secretaryID;
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
        private Integer secretaryID;
        private String secretaryName;
        private String secretarySurname;
        private String secretaryPhoneNumber;
        private String secretaryEmail;

        private Builder() {
        }

        public Builder withSecretaryID(Integer secretaryID) {
            this.secretaryID = secretaryID;
            return this;
        }

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
