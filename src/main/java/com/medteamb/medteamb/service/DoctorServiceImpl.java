package com.medteamb.medteamb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.service.dto.doctor.DoctorMapper;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;

@Service
public class DoctorServiceImpl implements DoctorService {

	private DoctorMapper docMapper;
	private DoctorRepository docRepo;
	
	public DoctorServiceImpl(DoctorMapper docMapper, DoctorRepository docRepo) {
		this.docMapper = docMapper;
		this.docRepo = docRepo;
	}
//CREATE
	@Override
	public DoctorRequestDTO saveDoctor(DoctorRequestDTO docDto) {
		Doctor doctor = docMapper.mapFrom(docDto);
		Doctor savedDoc = docRepo.save(doctor);
		return docMapper.mapTo(savedDoc);
	}
//READ ALL
	@Override
	public List<DoctorRequestDTO> showAllDocs() {
		List<Doctor>  doctors = docRepo.findAll();
		List<DoctorRequestDTO> result = new ArrayList<>();
		for (Doctor doc : doctors) {
			result.add(docMapper.mapTo(doc)) ;
		}
		return result ;
	}
//READ ONE
	@Override
	public Optional<DoctorRequestDTO> findDocById(Integer doctorID) {
	Optional<Doctor> foundDoc = docRepo.findById(doctorID);
	if(foundDoc.isEmpty()) return Optional.empty();
	return foundDoc.map(doc->{
		return docMapper.mapTo(doc);
	});
	}
//CHECK EXISTANCE BEFORE UPDATE
	@Override
	public Boolean exists(Integer doctorID) {
		return docRepo.existsById(doctorID);
	}
//DELETE
	@Override
	public void deleteDoc(Integer doctorID) {
		docRepo.deleteById(doctorID);
	}

}
