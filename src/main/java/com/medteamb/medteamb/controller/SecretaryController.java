package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.service.SecretaryService;
import com.medteamb.medteamb.service.dto.secretary.SecretaryRequestDTO;
import com.medteamb.medteamb.service.dto.secretary.SecretaryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secretaries")
public class SecretaryController {

    private final SecretaryService secretaryService;

    @Autowired
    public SecretaryController(SecretaryService secretaryService) {
        this.secretaryService = secretaryService;
    }

    @GetMapping
    public ResponseEntity<List<SecretaryResponseDTO>> getAllSecretaries() {
        List<SecretaryResponseDTO> secretaries = secretaryService.getAllSecretaries();
        return new ResponseEntity<>(secretaries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretaryResponseDTO> getSecretaryById(@PathVariable Long id) {
        SecretaryResponseDTO secretary = secretaryService.getSecretaryById(id.intValue());
        return new ResponseEntity<>(secretary, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SecretaryResponseDTO> createSecretary(@RequestBody SecretaryRequestDTO secretaryDTO) {
        SecretaryResponseDTO createdSecretary = secretaryService.createSecretary(secretaryDTO);
        return new ResponseEntity<>(createdSecretary, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecretaryResponseDTO> updateSecretary(@PathVariable Long id, @RequestBody SecretaryRequestDTO secretaryDTO) {
        SecretaryResponseDTO updatedSecretary = secretaryService.updateSecretary(id.intValue(), secretaryDTO);
        return new ResponseEntity<>(updatedSecretary, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecretary(@PathVariable Long id) {
        secretaryService.deleteSecretary(id.intValue());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
