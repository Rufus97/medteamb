package com.medteamb.medteamb.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.model.agenda.Appointment;
import com.medteamb.medteamb.repository.AppointmentRepository;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.repository.SecretaryRepository;
import com.medteamb.medteamb.service.dto.appointment.AppointmentListDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.appointment.DTOAppointmentMapper;

@Service
@EnableScheduling
public class AppointmentService {
	
	private DTOAppointmentMapper dtoMapper;
	private AppointmentRepository appointmentRepository;
	private DoctorRepository doctorRepository;
	private PatientRepository patientRepository;
	private SecretaryRepository secretaryRepository;
	
	public AppointmentService(DTOAppointmentMapper dtoMapper, AppointmentRepository appointmentRepository,
			DoctorRepository doctorRepository, PatientRepository patientRepository,
			SecretaryRepository secretaryRepository) {
		this.dtoMapper = dtoMapper;
		this.appointmentRepository = appointmentRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.secretaryRepository = secretaryRepository;
	}

	public void createAppointmentsPerDay(LocalDateTime now, LocalTime beginningTime, 
											Integer appointmentDuration, Integer appointmentsPerDay, 
												Doctor doctor){
        LocalDateTime hourSlot = LocalDateTime.of(now.toLocalDate(),
        											LocalTime.of(beginningTime.getHour(),
        														beginningTime.getMinute()));
        for (int i = 1; i <= appointmentsPerDay; i++){
            Appointment appointment = new Appointment(now, doctor);
            appointmentRepository.save(appointment);
            hourSlot = hourSlot.plusMinutes(appointmentDuration);
        }
    }
	
	public void createAppointmentsPerMonth(LocalDateTime now, LocalTime beginningTime, 
									Integer appointmentsDuration, Integer appointmentsPerDay, 
										Integer agendaMonthsRange, Doctor doctor){
        while(now.toLocalDate().isBefore(LocalDate.now().plusMonths(agendaMonthsRange))){
        	createAppointmentsPerDay(now, beginningTime, appointmentsDuration, appointmentsPerDay, doctor);
            now = now.plusDays(1);
        }
    }
	
	@Scheduled(fixedRate = 2000L)
	public void scheduledAppointmentCreator() {
		Iterable<Integer> medicsWithoutAgenda = appointmentRepository.getAllDoctorsWithoutAgenda();
		medicsWithoutAgenda.forEach( doctorID -> {
			Doctor doctor = doctorRepository.findById(doctorID).get();
			createAppointmentsPerMonth(LocalDateTime.now(),
										doctor.getBeginningWorkTime(), doctor.getAppointmentsDuration(),
											doctor.getAppointmentsPerDay(), doctor.getAgendaMonthsRange(),
												doctorRepository.findById(doctorID).get());
		});
		List<Integer> doctorsWithAgenda = new ArrayList<>();
		appointmentRepository.getAllDoctorsWithAgenda().forEach(doctorsWithAgenda::add);
		doctorsWithAgenda.forEach( doctorID -> {
			Doctor doctor = doctorRepository.findById(doctorID).get();
			createMonthlyAgendaForEachDoctor(doctor.getAgendaMonthsRange(), doctor.getBeginningWorkTime(),
												doctor.getAppointmentsDuration(), doctor.getAppointmentsPerDay(), 
													appointmentRepository.lastDoctorAppointmentByID(doctorID));
		});
	}
	
	private void createMonthlyAgendaForEachDoctor(Integer appointmentsRange, LocalTime beginningWorkTime, 
														Integer appointmentsDuration, Integer appointmentsPerDay, 
															Optional<Appointment> lastDoctorAppointment) { 
		LocalDateTime appointmentDateTime = lastDoctorAppointment.get().getAppointmentDateTime();
		if(appointmentDateTime.plusMonths(appointmentsRange)
				.isBefore(LocalDateTime.now().plusMonths(appointmentsRange))) {
			createAppointmentsPerMonth(appointmentDateTime.plusMonths(appointmentsRange), 
					beginningWorkTime, appointmentsDuration, appointmentsPerDay, 
													appointmentsRange, lastDoctorAppointment.get().getDoctor());
		}
	}

	public AppointmentListDTO getAllAppointments() {
		return new AppointmentListDTO(appointmentRepository.findAll());
	}
	
	public AppointmentResponseDTO getAppointmentByID(Integer appointmentID) {
		Appointment appointment = appointmentRepository.findById(appointmentID).get();
		return dtoMapper.appointmentToResponse(appointment);
	}
	
	public AppointmentResponseDTO updateAppointment(Integer appointmentID, AppointmentRequestDTO appointmentRequest) {
		if((appointmentRepository.findById(appointmentID)).isPresent()) {
			Appointment appointment = appointmentRepository.findById(appointmentID).get();
			appointment.setAppointmentDateTime(appointmentRequest.getAppointmentDateTime());
			appointment.setLocation(appointmentRequest.getLocation());
			appointment.setMedicalService(appointmentRequest.getMedicalService());
			appointment.setStatus(appointmentRequest.getStatus());
			appointment.setDoctor(doctorRepository.findById(appointmentRequest.getDoctor()).get());
			appointment.setSecretary(secretaryRepository.findById(appointmentRequest.getSecretary()).get());
			appointment.setPatient(patientRepository.findById(appointmentRequest.getPatient()).get());
			appointment.setTaxCode(patientRepository.findById(appointmentRequest.getPatient()).get().getTaxCode());
			appointment = appointmentRepository.save(appointment);
			return dtoMapper.appointmentToResponse(appointment);
		}
		else return null;
	}
	
	public AppointmentResponseDTO deleteAppointment(Integer appointmentID) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentID);
		if (appointment.isPresent()) {
			appointmentRepository.deleteById(appointment.get().getAppointmentID());
			return dtoMapper.appointmentToResponse(appointment.get());
		}
		else return null;
		
	}
	
}
