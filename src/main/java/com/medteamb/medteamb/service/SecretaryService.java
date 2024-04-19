package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Secretary;
import com.medteamb.medteamb.repository.SecretaryRepository;
import com.medteamb.medteamb.service.dto.secretary.SecretaryDTOMapper;
import com.medteamb.medteamb.service.dto.secretary.SecretaryRequestDTO;
import com.medteamb.medteamb.service.dto.secretary.SecretaryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;
    private final SecretaryDTOMapper secretaryDTOMapper;

    public SecretaryService(SecretaryRepository secretaryRepository, SecretaryDTOMapper secretaryDTOMapper) {
        this.secretaryRepository = secretaryRepository;
        this.secretaryDTOMapper = secretaryDTOMapper;
    }

    public List<SecretaryResponseDTO> getAllSecretaries() {
        List<Secretary> secretaries = secretaryRepository.findAll();
        return secretaries.stream()
                .map(secretaryDTOMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public SecretaryResponseDTO getSecretaryById(Integer id) {
        Secretary secretary = secretaryRepository.findById(id).orElseThrow();
        return secretaryDTOMapper.mapToResponseDTO(secretary);
    }

    public SecretaryResponseDTO createSecretary(SecretaryRequestDTO secretaryDTO) {
        Secretary secretary = secretaryDTOMapper.mapFromRequestDTO(secretaryDTO);
        Secretary savedSecretary = secretaryRepository.save(secretary);
        return secretaryDTOMapper.mapToResponseDTO(savedSecretary);
    }

    public SecretaryResponseDTO updateSecretary(Integer id, SecretaryRequestDTO secretaryDTO) {
        Secretary existingSecretary = secretaryRepository.findById(id).orElseThrow();

        existingSecretary.setSecretaryName(secretaryDTO.getSecretaryName());
        existingSecretary.setSecretarySurname(secretaryDTO.getSecretarySurname());
        existingSecretary.setSecretaryPhoneNumber(secretaryDTO.getSecretaryPhoneNumber());
        existingSecretary.setSecretaryEmail(secretaryDTO.getSecretaryEmail());

        Secretary updatedSecretary = secretaryRepository.save(existingSecretary);
        return secretaryDTOMapper.mapToResponseDTO(updatedSecretary);
    }

    public void deleteSecretary(Integer id) {
        secretaryRepository.deleteById(id);
    }


}