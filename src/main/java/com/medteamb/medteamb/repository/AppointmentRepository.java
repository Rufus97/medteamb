package com.medteamb.medteamb.repository;

import java.time.LocalDateTime;
import java.util.Optional;


import com.medteamb.medteamb.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.medteamb.medteamb.model.agenda.Appointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query(value = 	"select d.doctor_id from appointment a right join doctor d " +
					"on a.doctor_id  = d.doctor_id where a.doctor_id is null", nativeQuery = true)
	Iterable<Long> getAllDoctorsWithoutAgenda();

	@Query(value = 	"select d.doctor_id from appointment a join doctor d " +
					"on a.doctor_id = d.doctor_id group by d.doctor_id", nativeQuery = true)
    Iterable<Long> getAllDoctorsWithAgenda();

	@Query(value =  "select a.* from appointment a join doctor d " + 
					"on d.doctor_id = a.doctor_id where d.doctor_id = ?1 " + 
					"order by a.appointment_date_time desc limit 1", nativeQuery = true)
	Optional<Appointment> lastDoctorAppointmentByID(Long id);

	// Alfred Queries

	// get all avaible from doc id
	@Query(value = "select a.* from appointment as a join doctor as d " +
			"on d.doctor_id = a.doctor_id " +
			"where d.doctor_id = ?1 and  a.status = 'AVAIBLE'" ,nativeQuery = true,
			countQuery = "select count(a.id) from appointment as a join doctor as d " +
					"on d.doctor_id = a.doctor_id " +
					"where d.doctor_id = ?1 and a.status = 'AVAIBLE'")
	Page<Appointment> getALlAvaibleAppointmentsOfOneDoctor(Long id, PageRequest page);

	// get all avaible from doc name and surname
	@Query(value = "select a.* from appointment a join doctor d " +
			"on d.doctor_id = a.doctor_id " +
			"where d.doctor_name  = ?1 and d.doctor_surname = ?2 and a.status = 'AVAIBLE' ", nativeQuery = true,
			countQuery = "select count(a.id) from appointment as a join doctor as d " +
					"on d.doctor_id = a.doctor_id " +
					"where d.doctor_name  = ?1 and d.doctor_surname = ?2 and a.status = 'AVAIBLE' ")
	Page<Appointment> getAllAvaibleAppointmentsOfOneDocNameAndSurname(String name, String surname
			, PageRequest page);

	// get all doctor appointment from doctor request
	@Query(value = "select a.* from appointment a " +
			"where a.doctor_id = ?1 and a.status = 'TO_DO'", nativeQuery = true)
	Page<Appointment> getAllMyAppointments(Long id, PageRequest page);

	// get all patient from doctor id
	@Query(value = "select p.* from patient p join appointment a " +
			"on p.patientid = a.patient_id where a.doctor_id = ?1 " +
			"group by p.patientid", nativeQuery = true)
	Page<Patient> getAllMyPatients(Long id, PageRequest page);


	// get appointments from patient id
	@Query(value = "select a.* from appointment a join patient p on p.patientid  = a.patient_id " +
			"where p.patientid  = ?1 and a.status  = 'TO_DO' " , nativeQuery = true, countQuery =
			"select count(a.id) from appointment a join patient p on p.patientid  = a.patient_id " +
					"where p.patientid  = ?1 and a.status  = 'TO_DO' ")
	Page<Appointment> getAllPatientAppointments(Integer id, PageRequest page);

	// get history of patient id appointments
	@Query(value = "select a.* from appointment a join patient p on p.patientid  = a.patient_id " +
			"where p.patientid  = ?1 and a.status  = 'PASSED' "
			, nativeQuery = true, countQuery =
			"select count(a.id) from appointment a join patient p on p.patientid  = a.patient_id " +
					"where p.patientid  = ?1 and a.status  = 'PASSED' ")
	Page<Appointment> getHistoryOfPatientAppointmentsById(Integer id, PageRequest page);

	Optional<Appointment> findByAppointmentDateTime(LocalDateTime day);

}
