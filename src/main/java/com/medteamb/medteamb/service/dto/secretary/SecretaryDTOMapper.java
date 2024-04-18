package com.medteamb.medteamb.service.dto.secretary;

import com.medteamb.medteamb.model.Secretary;
import org.springframework.stereotype.Component;
@Component
public class SecretaryDTOMapper {

    public SecretaryRequestDTO mapTo(Secretary secretary) {
        SecretaryRequestDTO secretaryDTO = SecretaryRequestDTO.builder()
                .withSecretaryID(secretary.getSecretaryID())
                .withSecretaryName(secretary.getSecretaryName())
                .withSecretaryPhoneNumber(secretary.getSecretarySurname())
                .withSecretaryPhoneNumber(secretary.getSecretaryPhoneNumber())
                .withSecretaryEmail(secretary.getSecretaryEmail())
                .build();
        return secretaryDTO;
    }
}
