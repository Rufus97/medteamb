package com.medteamb.medteamb.service.dto.doctor;

import com.medteamb.medteamb.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

	public DoctorRequestDTO mapFromDocToDTO(Doctor doctor) {
		DoctorRequestDTO doctorDto = DoctorRequestDTO.builder()
				.withDoctorID(doctor.getDoctorID())
				.withDoctorName(doctor.getDoctorName())
				.withDoctorSurname(doctor.getDoctorSurname())
				.withDoctorEmail(doctor.getDoctorEmail())
				.withDoctorPhoneNumber(doctor.getDoctorPhoneNumber())
				.withSpecialization(doctor.getSpecialization())
				.build();
		return doctorDto;
		
	}
	
	public Doctor mapFrom(DoctorRequestDTO doctorDto) {
		Doctor doctor = new Doctor();
		doctor.setDoctorName(doctorDto.getDoctorName());
		doctor.setDoctorSurname(doctorDto.getDoctorSurname());
		doctor.setDoctorEmail(doctorDto.getDoctorEmail());
		doctor.setDoctorPhoneNumber(doctorDto.getDoctorPhoneNumber());
		doctor.setSpecialization(doctorDto.getSpecialization());
		return doctor;
	}
}
