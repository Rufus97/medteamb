package com.medteamb.medteamb.service.dto.doctor;

import org.springframework.stereotype.Component;

import com.medteamb.medteamb.model.Doctor;

@Component
public class DoctorMapper {

	public Doctor mapFromRequestDTOToDoc(DoctorRequestDTO request) {
		Doctor doctor = new Doctor();
		doctor.setDoctorID(request.getDoctorID());
		doctor.setDoctorName(request.getDoctorName());
		doctor.setDoctorSurname(request.getDoctorSurname());
		doctor.setDoctorPhoneNumber(request.getDoctorPhoneNumber());
		doctor.setDoctorEmail(request.getDoctorEmail());
		doctor.setSpecialization(request.getSpecialization());
		return doctor;
		
	}
	
	public DoctorResponseDTO mapFromDocToResponseDTO(Doctor response) {
		return DoctorResponseDTO.builder()
				.withDoctorName(response.getDoctorName())
				.withDoctorSurname(response.getDoctorSurname())
				.withDoctorPhoneNumber(response.getDoctorPhoneNumber())
				.withDoctorEmail(response.getDoctorEmail())
				.withSpecialization(response.getSpecialization())
				.withBeginningWorkTime(response.getBeginningWorkTime())
				.withAppointmentsDuration(response.getAppointmentsDuration())
				.withAppointmentsPerDay(response.getAppointmentsPerDay())
				.withAgendaMonthsRange(response.getAgendaMonthsRange())
				.build();
	}
}
