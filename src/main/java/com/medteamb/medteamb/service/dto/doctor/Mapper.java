package com.medteamb.medteamb.service.dto.doctor;

import com.medteamb.medteamb.model.Doctor;

public class Mapper {

	public DoctorRequestDTO mapTo(Doctor doctor) {
		DoctorRequestDTO doctorDto = DoctorRequestDTO.builder()
				.withDoctorID(doctor.getDoctorID())
				.withDoctorEmail(doctor.getDoctorName())
				.withDoctorSurname(doctor.getDoctorSurname())
				.withDoctorEmail(doctor.getDoctorEmail())
				.withDoctorPhoneNumber(doctor.getDoctorPhoneNumber())
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
