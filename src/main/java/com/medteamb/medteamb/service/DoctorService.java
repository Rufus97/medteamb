package com.medteamb.medteamb.service;

import java.util.List;
import java.util.Optional;


import com.medteamb.medteamb.model.agenda.Appointment;
import com.medteamb.medteamb.repository.AppointmentRepository;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.NotFound;

import com.medteamb.medteamb.service.ResponseHandler.Response;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.DTOmapper;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.doctor.DoctorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.DoctorRepository;

import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;

@Service
public class DoctorService {



	private DTOmapper mapper;
	private DoctorRepository docRepo;

	private AppointmentRepository appointmentRepo;
	
	public DoctorService(DTOmapper mapper, DoctorRepository docRepo, AppointmentRepository appointmentRepo) {
		this.docRepo = docRepo;
		this.appointmentRepo = appointmentRepo;
		this.mapper = mapper;
	}
//CREATE

	public  Response<DoctorResponseDTO> newDoctor(DoctorRequestDTO docDto) {
		Doctor doctor = mapper.mapFromRequestToDoc(docDto);
		Doctor savedDoc = docRepo.save(doctor);
		return new Response<DoctorResponseDTO> (mapper.mapFromDocToResponse(savedDoc)) ;
	}
//READ ALL
	public ResponseForLists<AppointmentResponseDTO> getMyAppointments(Long id, int page, int size) {
		Page<Appointment> list = appointmentRepo.getAllMyAppointments(id, PageRequest.of(page,size));
		ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapFromIterableToAppointmentResponse(list.toList()));
		response.setCurrentPage(list.getNumber());
		response.setNumOfPages(list.getTotalPages());
		response.setNumOfElements(list.getNumberOfElements());
		response.setTotalElements(list.getTotalElements());
		return response;
	}

	public ResponseForLists<DoctorResponseDTO> showAllDocs(int page, int size) {
		Page<Doctor>  doctors = docRepo.findAll(PageRequest.of(page, size));

		ResponseForLists<DoctorResponseDTO> response = new ResponseForLists<>(mapper.mapFromIterableToDocResponse(doctors.toList()));
		response.setCurrentPage(doctors.getNumber());
		response.setNumOfPages(doctors.getTotalPages());
		response.setNumOfElements(doctors.getNumberOfElements());
		response.setTotalElements(doctors.getTotalElements());
	    return response;
	}
//READ ONE

	public Optional<DoctorResponseDTO> findDocById(Long doctorID) {
	Optional<Doctor> foundDoc = docRepo.findById(doctorID);
	if(foundDoc.isEmpty()) return Optional.empty();
	return foundDoc.map(doc->{
		return mapper.mapFromDocToResponse(doc);
	});
	}
//CHECK EXISTANCE BEFORE UPDATE

	public Boolean exists(Long doctorID) {
		return docRepo.existsById(doctorID);
	}

	public DoctorResponseDTO updateDoctorById(Doctor newDoc, Long id){
		Doctor doc = docRepo.findById(id).
				orElseThrow(() -> new NotFound("doctor not found"));
		doc.updateThisDocto(newDoc);
		return mapper.mapFromDocToResponse(doc);
	}
//DELETE

	public void deleteDoc(Long doctorID) {
		docRepo.deleteById(doctorID);
	}


}
