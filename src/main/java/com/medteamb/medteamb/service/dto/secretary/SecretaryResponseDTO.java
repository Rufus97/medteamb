package com.medteamb.medteamb.service.dto.secretary;

public class SecretaryResponseDTO {

    private Integer secretaryId;
    private String secretaryName;
    private String secretarySurname;
    private String secretaryPhoneNumber;
    private String secretaryEmail;

    public Integer getSecretaryId() {
        return secretaryId;
    }

    public void setSecretaryId(Integer secretaryId) {
        this.secretaryId = secretaryId;
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
}
