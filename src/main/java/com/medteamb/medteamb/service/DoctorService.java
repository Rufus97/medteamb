package com.medteamb.medteamb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotFound;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.service.dto.doctor.DoctorMapper;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;

@Service
public class DoctorService {

	private DoctorMapper docMapper;
	private DoctorRepository docRepo;
	
	public DoctorService(DoctorMapper docMapper, DoctorRepository docRepo) {
		this.docMapper = docMapper;
		this.docRepo = docRepo;
	}
//CREATE

	public DoctorRequestDTO saveDoctor(DoctorRequestDTO docDto) {
		Doctor doctor = docMapper.mapFrom(docDto);
		Doctor savedDoc = docRepo.save(doctor);
		return docMapper.mapFromDocToDTO(savedDoc);
	}
//READ ALL

	public List<DoctorRequestDTO> showAllDocs() {
		List<Doctor>  doctors = docRepo.findAll();
		List<DoctorRequestDTO> result = new ArrayList<>();
		for (Doctor doc : doctors) {
			result.add(docMapper.mapFromDocToDTO(doc)) ;
		}
		return result ;
	}
//READ ONE

	public Optional<DoctorRequestDTO> findDocById(Integer doctorID) {
	Optional<Doctor> foundDoc = docRepo.findById(doctorID);
	if(foundDoc.isEmpty()) return Optional.empty();
	return foundDoc.map(doc->{
		return docMapper.mapFromDocToDTO(doc);
	});
	}
//CHECK EXISTANCE BEFORE UPDATE

	public Boolean exists(Integer doctorID) {
		return docRepo.existsById(doctorID);
	}

	public DoctorRequestDTO updateDoctorById(Doctor newDoc, Integer id){
		Doctor doc = docRepo.findById(id).
				orElseThrow(() -> new PatientNotFound("doctor not found"));
		doc.updateThisDocto(newDoc);
		return docMapper.mapFromDocToDTO(doc);
	}
//DELETE

	public void deleteDoc(Integer doctorID) {
		docRepo.deleteById(doctorID);
	}

}
