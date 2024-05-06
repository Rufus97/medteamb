package com.medteamb.medteamb.repository.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.patient.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // MethodName queries
    Optional<Patient> findByPatientEmail(String patientEmail);
    Optional<Patient> findBypatientName(String patientName);
    Optional<Patient> findBypatientSurname(String patientSurname);
    Optional<Patient> findBytaxCode(String taxCode);
    Optional<Patient> findBypatientPhoneNumber(String phoneNumber);
}
