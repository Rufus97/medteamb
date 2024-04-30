package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CalendarRepo extends JpaRepository<AppointmentSlot, Long> {

    @Query(value = "select d.doctor_id from slot_appuntamento sa right join doctor d " +
            "on sa.doctor_id  = d.doctor_id where sa.doctor_id is null", nativeQuery = true)
    Iterable<Long> getAllDcotorWhoDontHaveAgenda();


   @Query(value = "select d.doctor_id from slot_appuntamento sa join doctor d " +
           " on sa.doctor_id  = d.doctor_id group by d.doctor_id", nativeQuery = true)
    Iterable<Long> getAllDocsWithAgenda();

   @Query(value =  "select sa.* from slot_appuntamento sa join doctor d on d.doctor_id = sa.doctor_id where d.doctor_id = ?1 order by sa.giorno desc limit 1", nativeQuery = true)
   Optional<AppointmentSlot> updateDoc(Long id);

}