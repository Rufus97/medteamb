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
@RequestMapping("/secretaries")
public class SecretaryController {

    private final SecretaryService secretaryService;

    @Autowired
    public SecretaryController(SecretaryService secretaryService) {
        this.secretaryService = secretaryService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<SecretaryResponseDTO>> getAllSecretaries() {
        List<SecretaryResponseDTO> secretaries = secretaryService.getAllSecretaries();
        return new ResponseEntity<>(secretaries, HttpStatus.OK);
    }

    @GetMapping("/get/{secretaryId}")
    public ResponseEntity<SecretaryResponseDTO> getSecretaryById(@PathVariable Integer secretaryId) {
        SecretaryResponseDTO secretary = secretaryService.getSecretaryById(secretaryId);
        return new ResponseEntity<>(secretary, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SecretaryResponseDTO> createSecretary(@RequestBody SecretaryRequestDTO secretaryDTO) {
        SecretaryResponseDTO createdSecretary = secretaryService.createSecretary(secretaryDTO);
        return new ResponseEntity<>(createdSecretary, HttpStatus.CREATED);
    }

    @PutMapping("/update/{secretaryId}")
    public ResponseEntity<SecretaryResponseDTO> updateSecretary(@PathVariable Integer secretaryId, @RequestBody SecretaryRequestDTO secretaryDTO) {
        SecretaryResponseDTO updatedSecretary = secretaryService.updateSecretary(secretaryId, secretaryDTO);
        return new ResponseEntity<>(updatedSecretary, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{secretaryId}")
    public ResponseEntity<Void> deleteSecretary(@PathVariable Integer secretaryId) {
        secretaryService.deleteSecretary(secretaryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
