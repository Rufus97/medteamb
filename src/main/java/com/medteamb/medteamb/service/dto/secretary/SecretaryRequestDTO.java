package com.medteamb.medteamb.service.dto.secretary;

public class SecretaryRequestDTO {

    private String secretaryName;
    private String secretarySurname;
    private String secretaryPhoneNumber;
    private String secretaryEmail;


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
