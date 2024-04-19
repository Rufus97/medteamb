package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CalendarRepo extends JpaRepository<AppointmentSlot, Integer> {

    @Query(value = "select d.doctor_id from slot_appuntamento sa right join doctor d " +
            "on sa.doctor_id  = d.doctor_id where sa.doctor_id is null", nativeQuery = true)
    Iterable<Integer> getAllDcotorWhoDontHaveAgenda();

    @Query(value = "select sa.* from doctor d join slot_appuntamento sa " +
            "on d.doctor_id  = sa.doctor_id " +
            "group by sa.id " +
            "order by sa.giorno desc, sa.orario_finale  desc limit ?1", nativeQuery = true)
    Iterable<AppointmentSlot> getDocLastSlot(Integer id);

   @Query(value = "select d.doctor_id from slot_appuntamento sa join doctor d " +
           " on sa.doctor_id  = d.doctor_id group by d.doctor_id", nativeQuery = true)
    Iterable<Integer> getAllDocsWithAgenda();

}
