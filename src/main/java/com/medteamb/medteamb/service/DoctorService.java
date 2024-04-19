package com.medteamb.medteamb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;

@Service
public interface DoctorService {

	public DoctorRequestDTO saveDoctor (DoctorRequestDTO docDto);
	public List<DoctorRequestDTO> showAllDocs();
	public Optional<DoctorRequestDTO> findDocById(Integer doctorID);
	public Boolean exists(Integer doctorID);
	public void deleteDoc(Integer doctorID);


}
