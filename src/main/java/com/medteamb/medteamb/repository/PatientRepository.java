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



    // get all avaible from doc id
    @Query(value = "select * from slot_appuntamento sa join doctor d " +
            "on d.doctor_id = sa.doctor_id " +
            "where d.doctor_id = ?1 and sa.status = 'AVAIBLE'", nativeQuery = true)
    Iterable<AppointmentSlot> getALlAvaibleAppointmentsOfOneDoctor(Integer id);

    // get all avaible from doc name and surname
    @Query(value = "select * from slot_appuntamento sa join doctor d " +
            "on d.doctor_id = sa.doctor_id " +
            "where d.doctor_name  = ?1 and d.doctor_surname = ?2 and sa.status = 'AVAIBLE'", nativeQuery = true)
    Iterable<AppointmentSlot> getAllAvaibleAppointmentsOfOneDocNameAndSurname(String name, String surname);

    // get appointments from patient id
    @Query(value = "select * from slot_appuntamento sa join patient p on p.patientid  = sa.patient_id " +
            "where p.patientid  = ?1 and sa.status  = 'TO_DO' ", nativeQuery = true)
    Iterable<AppointmentSlot> getAllPatientAppointments(Integer id);

    // get appointment from day and patient id
    @Query(value = "select * from slot_appuntamento sa join patient p " +
            "on p.patientid = sa.patient_id " +
            "where sa.giorno = ?#(#day.giorno) and sa.orario_iniziale = ?#(#hour.orario_iniziale) " +
            "and sa.patient_id = ?#(#id.patient_id)", nativeQuery = true)
    Optional<AppointmentSlot> getOneAppointmentFromPatientIdAndDate(@Param("day") LocalDate day,
                                                                    @Param("hour")LocalTime hour,
                                                                    @Param("id")Integer id);
    // create new appointment with day and doctor // patient id to be replaced header request
    @Query(value = "update slot_appuntamento sa " +
            "set sa.patient_id = ?1, sa.status = 'TO_DO' " +
            "where sa.giorno = ?2 and sa.orario_iniziale = ?3 and sa.doctor_id = ?4", nativeQuery = true)
            AppointmentSlot createAppointmentWithDateAndHour(Integer id, LocalDate day, LocalTime hour, Integer docID);

    // MethodName queries
    Optional<Patient> findByPatientEmail(String patientEmail);
    Optional<Patient> findBypatientName(String patientName);
    Optional<Patient> findBypatientSurname(String patientSurname);
    Optional<Patient> findBytaxCode(String taxCode);
    Optional<Patient> findBypatientPhoneNumber(String phoneNumber);
}
