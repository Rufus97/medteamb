package com.medteamb.medteamb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer>{

	
}
