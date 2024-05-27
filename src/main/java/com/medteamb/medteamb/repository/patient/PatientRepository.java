package com.medteamb.medteamb.repository.patient;

import com.medteamb.medteamb.model.agenda.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.patient.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "SELECT * FROM Appointment a where a.patient_patientid = ?1", nativeQuery = true )
    public Iterable<Appointment> getAllPatientAppointments(Integer patient_id);

    @Query(value = "select * from patient p join user u " +
            "on p.user_id = u.id " +
            "where u.id = ?1", nativeQuery = true)
    public Optional<Patient> findPatientByUserId(Long id);



    // MethodName queries
    Optional<Patient> findByPatientEmail(String patientEmail);
    Optional<Patient> findBypatientName(String patientName);
    Optional<Patient> findBypatientSurname(String patientSurname);
    Optional<Patient> findBytaxCode(String taxCode);
    Optional<Patient> findBypatientPhoneNumber(String phoneNumber);
}
