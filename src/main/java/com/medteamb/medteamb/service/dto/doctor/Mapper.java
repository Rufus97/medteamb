package com.medteamb.medteamb.service.dto.doctor;

import com.medteamb.medteamb.model.Doctor;

public class Mapper {

	public DoctorRequestDTO mapTo(Doctor doctor) {
		DoctorRequestDTO doctorDto = DoctorRequestDTO.builder()
				.doctorID(doctor.getDoctorID())
				.doctorName(doctor.getDoctorName())
				.doctorSurname(doctor.getDoctorSurname())
				.doctorEmail(doctor.getDoctorEmail())
				.doctorPhoneNumber(doctor.getDoctorPhoneNumber())
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
