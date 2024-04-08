package com.medteamb.medteamb.service;

import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{
	
private PatientRepository patientRepository;
	
	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public Patient createPatient(Patient patient) {
		return patientRepository.save(patient);
	}


}
