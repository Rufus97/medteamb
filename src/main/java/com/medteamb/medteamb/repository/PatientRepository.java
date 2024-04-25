package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {





    // MethodName queries
    Optional<Patient> findByPatientEmail(String patientEmail);
    Optional<Patient> findBypatientName(String patientName);
    Optional<Patient> findBypatientSurname(String patientSurname);
    Optional<Patient> findBytaxCode(String taxCode);
    Optional<Patient> findBypatientPhoneNumber(String phoneNumber);
}
