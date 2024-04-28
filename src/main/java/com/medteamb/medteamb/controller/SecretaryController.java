package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.service.SecretaryService;
import com.medteamb.medteamb.service.dto.secretary.SecretaryRequestDTO;
import com.medteamb.medteamb.service.dto.secretary.SecretaryResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secretaries")
public class SecretaryController {

    private final SecretaryService secretaryService;

    public SecretaryController(SecretaryService secretaryService) {
        this.secretaryService = secretaryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SecretaryResponseDTO>> getAllSecretaries() {
        List<SecretaryResponseDTO> secretaries = secretaryService.getAllSecretaries();
        return new ResponseEntity<>(secretaries, HttpStatus.OK);
    }

    @GetMapping("/get/{secretaryID}")
    public ResponseEntity<SecretaryResponseDTO> getSecretaryById(@PathVariable Integer secretaryID) {
        SecretaryResponseDTO secretary = secretaryService.getSecretaryById(secretaryID);
        return new ResponseEntity<>(secretary, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SecretaryResponseDTO> createSecretary(@RequestBody SecretaryRequestDTO secretaryDTO) {
        SecretaryResponseDTO createdSecretary = secretaryService.createSecretary(secretaryDTO);
        return new ResponseEntity<>(createdSecretary, HttpStatus.CREATED);
    }

    @PutMapping("/update/{secretaryID}")
    public ResponseEntity<SecretaryResponseDTO> updateSecretary(@PathVariable Integer secretaryID, @RequestBody SecretaryRequestDTO secretaryDTO) {
        SecretaryResponseDTO updatedSecretary = secretaryService.updateSecretary(secretaryID, secretaryDTO);
        return new ResponseEntity<>(updatedSecretary, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{secretaryID}")
    public ResponseEntity<Void> deleteSecretary(@PathVariable Integer secretaryID) {
        secretaryService.deleteSecretary(secretaryID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
