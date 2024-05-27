package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.agenda.Appointment;
import com.medteamb.medteamb.model.patient.Patient;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    @Query(value = "select * from doctor d join user u " +
            "on d.user_id  = u.id " +
            "where u.id = ?1", nativeQuery = true)
    Optional<Doctor> findDoctorByUserId(Long id);

}

