package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentSlotRepo extends JpaRepository<AppointmentSlot, Long>{

    // get all avaible from doc id
    @Query(value = "select sa.* from slot_appuntamento as sa join doctor as d " +
            "on d.doctor_id = sa.doctor_id " +
            "where d.doctor_id = ?1 and sa.status = 'AVAIBLE'" ,nativeQuery = true,
            countQuery = "select count(sa.id) from slot_appuntamento as sa join doctor as d " +
                    "on d.doctor_id = sa.doctor_id " +
                    "where d.doctor_id = ?1 and sa.status = 'AVAIBLE'")
    Page<AppointmentSlot> getALlAvaibleAppointmentsOfOneDoctor(Integer id, PageRequest page);

    // get all avaible from doc name and surname
    @Query(value = "select sa.* from slot_appuntamento sa join doctor d " +
            "on d.doctor_id = sa.doctor_id " +
            "where d.doctor_name  = ?1 and d.doctor_surname = ?2 and sa.status = 'AVAIBLE' ", nativeQuery = true,
            countQuery = "select count(sa.id) from slot_appuntamento as sa join doctor as d " +
                    "on d.doctor_id = sa.doctor_id " +
                    "where d.doctor_name  = ?1 and d.doctor_surname = ?2 and sa.status = 'AVAIBLE' ")
    Page<AppointmentSlot> getAllAvaibleAppointmentsOfOneDocNameAndSurname(String name, String surname
    , PageRequest page);

    // get appointments from patient id
    @Query(value = "select sa.* from slot_appuntamento sa join patient p on p.patientid  = sa.patient_id " +
            "where p.patientid  = ?1 and sa.status  = 'TO_DO' " , nativeQuery = true, countQuery =
            "select count(sa.id) from slot_appuntamento sa join patient p on p.patientid  = sa.patient_id " +
                    "where p.patientid  = ?1 and sa.status  = 'TO_DO' ")
    Page<AppointmentSlot> getAllPatientAppointments(Integer id, PageRequest page);

    // get history of patient id appointments
    @Query(value = "select sa.* from slot_appuntamento sa join patient p on p.patientid  = sa.patient_id " +
            "where p.patientid  = ?1 and sa.status  = 'PASSED' "
            , nativeQuery = true, countQuery =
            "select count(sa.id) from slot_appuntamento sa join patient p on p.patientid  = sa.patient_id " +
                    "where p.patientid  = ?1 and sa.status  = 'PASSED' ")
    Page<AppointmentSlot> getHistoryOfPatientAppointmentsById(Integer id, PageRequest page);

    // get appointment from day and patient id
    @Query(value = "select sa.* from slot_appuntamento sa join patient p " +
            "on p.patientid = sa.patient_id " +
            "where sa.giorno = ?#(#day.giorno) and sa.orario_iniziale = ?#(#hour.orario_iniziale) " +
            "and sa.patient_id = ?#(#id.patient_id)", nativeQuery = true)
    Optional<AppointmentSlot> getOneAppointmentFromPatientIdAndDate(@Param("day") LocalDate day,
                                                                    @Param("hour") LocalTime hour,
                                                                    @Param("id")Integer id);

    AppointmentSlot findBytoday(LocalDate day);
}
