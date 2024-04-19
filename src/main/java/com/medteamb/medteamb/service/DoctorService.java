package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.service.dto.doctor.DoctorMapper;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

	private DoctorRepository doctorRepository;
	private final DoctorMapper doctorMapper;

	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorMapper = new DoctorMapper();
		this.doctorRepository = doctorRepository;
	}

	// CREATE
	public DoctorRequestDTO newDoctor(DoctorRequestDTO newDoctorDTO) {
		Doctor doctor = doctorMapper.mapFrom(newDoctorDTO);
		return doctorMapper.mapTo(doctorRepository.save(doctor));
	}

	// READ
	public List<DoctorRequestDTO> getAllDoctors() {
		List<Doctor> doctors = doctorRepository.findAll();
		return doctors.stream().map(doctorMapper::mapTo).collect(Collectors.toList());
	}

}
