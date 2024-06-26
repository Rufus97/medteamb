package com.medteamb.medteamb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.service.ExceptionHandler.doctorExceptions.DoctorNotFoundException;
import com.medteamb.medteamb.service.dto.doctor.DoctorMapper;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;
import com.medteamb.medteamb.service.dto.doctor.DoctorResponseDTO;

@Service
public class DoctorService {

	private DoctorMapper docMapper;
	private DoctorRepository docRepo;
	
	public DoctorService(DoctorMapper docMapper, DoctorRepository docRepo) {
		this.docMapper = docMapper;
		this.docRepo = docRepo;
	}
//CREATE

	public DoctorResponseDTO saveDoctor(DoctorRequestDTO docDto) {
		Doctor doctor = docMapper.mapFromRequestDTOToDoc(docDto);
		Doctor savedDoc = docRepo.save(doctor);
		return docMapper.mapFromDocToResponseDTO(savedDoc);
	}
//READ ALL

	public List<DoctorResponseDTO> showAllDocs() {
		List<Doctor>  doctors = docRepo.findAll();
		List<DoctorResponseDTO> result = new ArrayList<>();
		for (Doctor doc : doctors) {
			result.add(docMapper.mapFromDocToResponseDTO(doc)) ;
		}
		return result ;
	}
//READ ONE

	public DoctorResponseDTO findDocById(Integer doctorID) {
	Doctor foundDoc = docRepo.findById(doctorID).orElseThrow(()->
	new DoctorNotFoundException("Doctor not found"));
	return docMapper.mapFromDocToResponseDTO(foundDoc);
	}
//CHECK EXISTANCE BEFORE UPDATE

	public Boolean exists(Integer doctorID) {
		return docRepo.existsById(doctorID);
	}

	public DoctorResponseDTO updateDoctorById(Doctor newDoc, Integer id){
		Doctor doc = docRepo.findById(id).
				orElseThrow(() -> new DoctorNotFoundException(id));
		doc.updateThisDocto(newDoc);
		return docMapper.mapFromDocToResponseDTO(doc);
	}
	
	public DoctorResponseDTO updateDoctor(DoctorRequestDTO docDto, Integer id){
		 docDto.setDoctorID(id); 
		if(docRepo.findById(id).isEmpty()) {
			throw new DoctorNotFoundException(id);
		}
		Doctor doc = docMapper.mapFromRequestDTOToDoc(docDto);
		return DoctorResponseDTO.builder()
				.withDoctorName(doc.getDoctorName())
				.withDoctorSurname(doc.getDoctorSurname())
				.withDoctorPhoneNumber(doc.getDoctorPhoneNumber())
				.withDoctorEmail(doc.getDoctorEmail())
				.withBeginningWorkTime(doc.getBeginningWorkTime())
				.withAppointmentsPerDay(doc.getAppointmentsPerDay())
				.withAppointmentsDuration(doc.getAppointmentsDuration())
				.withAgendaMonthsRange(doc.getAgendaMonthsRange())
				.build();
	}
//DELETE

	public void deleteDoc(Integer doctorID) {
		docRepo.deleteById(doctorID);
	}

}
