package com.medteamb.medteamb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
