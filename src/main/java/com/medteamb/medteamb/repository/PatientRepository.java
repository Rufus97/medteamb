package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT * FROM Appointment a where a.patient_patientid = ?1", nativeQuery = true )
    public Iterable<Appointment> getAllPatientAppointments(Integer patient_id);

    Optional<Patient> findByPatientEmail(String patientEmail);

}
