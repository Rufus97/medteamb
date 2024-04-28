package com.medteamb.medteamb.repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


	// get all avaible from doc id
	@Query(value = "select  a.* from appointment a join doctor d " +
			"on d.doctor_id = a.doctor_id " +
			"where d.doctor_id = ?1 and a.status = 'AVAIBLE'",nativeQuery = true)
	Iterable<Appointment> getALlAvaibleAppointmentsOfOneDoctor(Integer id);

	// get all avaible from doc name and surname
	@Query(value = "select  a.* from appointment a sa join doctor d " +
			"on d.doctor_id = a.doctor_id " +
			"where d.doctor_name  = ?1 and d.doctor_surname = ?2 and a.status = 'AVAIBLE'", nativeQuery = true)
	Iterable<Appointment> getAllAvaibleAppointmentsOfOneDocNameAndSurname(String name, String surname);

	// get appointments from patient id
	@Query(value = "select a.* from appointment a  join patient p on p.patientid  = a.patient_id " +
			"where p.patientid  = ?1 and a.status  = 'TO_DO' ", nativeQuery = true)
	Iterable<Appointment> getAllPatientAppointments(Integer id);

	// get history of patient id appointments
	@Query(value = "select a.* from appointment a join patient p on p.patientid  = a.patient_id " +
			"where p.patientid  = ?1 and a.status  = 'PASSED' ", nativeQuery = true)
	Iterable<Appointment> getHistoryOfPatientAppointmentsById(Integer id);


	Appointment findByappointmentDateTime(LocalDateTime day);
}
