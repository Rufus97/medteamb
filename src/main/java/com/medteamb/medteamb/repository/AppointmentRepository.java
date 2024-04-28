package com.medteamb.medteamb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.agenda.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query(value = 	"select d.doctor_id from appointment a right join doctor d " +
					"on a.doctor_id  = d.doctor_id where a.doctor_id is null", nativeQuery = true)
	Iterable<Integer> getAllDoctorsWithoutAgenda();

	@Query(value = 	"select d.doctor_id from appointment a join doctor d " +
					"on a.doctor_id = d.doctor_id group by d.doctor_id", nativeQuery = true)
    Iterable<Integer> getAllDoctorsWithAgenda();

	@Query(value =  "select a.* from appointment a join doctor d " + 
					"on d.doctor_id = a.doctor_id where d.doctor_id = ?1 " + 
					"order by a.giorno desc limit 1", nativeQuery = true)
	Optional<Appointment> lastDoctorAppointmentByID(Integer id); // ma perche n'a update doctor proprio qua...?
	
}
