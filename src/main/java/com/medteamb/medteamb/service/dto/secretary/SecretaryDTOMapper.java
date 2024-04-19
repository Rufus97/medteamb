package com.medteamb.medteamb.service.dto.secretary;

import com.medteamb.medteamb.model.Secretary;
import org.springframework.stereotype.Component;

@Component
public class SecretaryDTOMapper {

    public SecretaryResponseDTO mapToResponseDTO(Secretary secretary) {
        if (secretary == null) {
            return null;
        }
        SecretaryResponseDTO dto = new SecretaryResponseDTO();
        dto.setSecretaryId(secretary.getSecretaryID());
        dto.setSecretaryName(secretary.getSecretaryName());
        dto.setSecretarySurname(secretary.getSecretarySurname());
        dto.setSecretaryPhoneNumber(secretary.getSecretaryPhoneNumber());
        dto.setSecretaryEmail(secretary.getSecretaryEmail());
        return dto;
    }

    public Secretary mapFromRequestDTO(SecretaryRequestDTO secretaryDTO) {
        if (secretaryDTO == null) {
            return null;
        }
        Secretary secretary = new Secretary();
        secretary.setSecretaryName(secretaryDTO.getSecretaryName());
        secretary.setSecretarySurname(secretaryDTO.getSecretarySurname());
        secretary.setSecretaryPhoneNumber(secretaryDTO.getSecretaryPhoneNumber());
        secretary.setSecretaryEmail(secretaryDTO.getSecretaryEmail());
        return secretary;
    }
}
