package com.medteamb.medteamb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.medteamb.medteamb.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

}
