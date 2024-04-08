package com.medteamb.medteamb.repository;

import org.springframework.data.repository.CrudRepository;

import com.medteamb.medteamb.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
