package com.medteamb.medteamb.service;

import java.util.List;
import java.util.Optional;


import com.medteamb.medteamb.model.agenda.Appointment;
import com.medteamb.medteamb.model.agenda.AppointmentStatus;
import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.repository.AppointmentRepository;
import com.medteamb.medteamb.repository.patient.PatientRepository;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.ConflictException;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.NotFound;

import com.medteamb.medteamb.service.ResponseHandler.Response;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.DTOmapper;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestAppointmentDTO;
import com.medteamb.medteamb.service.dto.doctor.DoctorResponseDTO;
import com.medteamb.medteamb.service.dto.patient.PatientRequestAppointment;
import com.medteamb.medteamb.service.dto.patient.PatientResponseDTO;
import com.medteamb.medteamb.service.dto.patient.PatientUpdateAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.DoctorRepository;

import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;

import javax.print.Doc;

@Service
public class DoctorService {



	private DTOmapper mapper;
	private DoctorRepository docRepo;
    private PatientRepository patientRepo;
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
	// update patient appointment from doctor DELETE
public AppointmentResponseDTO cancelAppointment(Long id, PatientRequestAppointment requestAppointment) {
	Appointment appointment = appointmentRepo.findById(requestAppointment.getAppointmentID()).get();
	Doctor patient = docRepo.findById(id).orElseThrow(
			() -> new NotFound("doctor not found " + id)
	);
	boolean check = appointment.getDoctor().getDoctorID() == id;
	if (check) {
		appointment.setPatient(null);
		appointment.setStatus(AppointmentStatus.EMPTY);
		appointment.setMedicalService(null);
		appointment.setLocation(null);
		appointment.setTaxCode(null);
		appointmentRepo.save(appointment);
		return mapper.mapFromAppointmentToResponseDTO(appointment);
	} else {
		throw new ConflictException("this appointment " + appointment + " dosen't belong to this doctor " + patient.getDoctorID());
	}
}
	// update appointment from doctor CREATE
	public AppointmentResponseDTO createAppointment(Long id, DoctorRequestAppointmentDTO requestAppointment) {
		Appointment appointment = appointmentRepo.findById(requestAppointment.getAppointmentID()).get();
		Doctor doctor = docRepo.findById(id).orElseThrow(
				() -> new NotFound("doctor not found " + id)
		);
		Patient patient = patientRepo.findById(requestAppointment.getPatientID()).orElseThrow(
				() -> new NotFound("patient not found " + id)
		);
		boolean check = appointment.getDoctor().getDoctorID() == id;
		if (check) {
			appointment.setPatient(patient);
			appointment.setStatus(AppointmentStatus.TO_DO);
			appointment.setMedicalService(requestAppointment.getMedicalService());
			appointment.setLocation(requestAppointment.getLocation());
			appointment.setTaxCode(requestAppointment.getTaxCode());
			appointmentRepo.save(appointment);
			return mapper.mapFromAppointmentToResponseDTO(appointment);
		} else {
			throw new ConflictException("this appointment " + appointment + " dosen't belong to this patient " + patient.getPatientID());
		}
	}

	// update appointment from doc move
	// move
	public AppointmentResponseDTO moveAppointment(long id, PatientUpdateAppointment request) {
		Patient patient = patientRepo.findById(request.getPatientID()).get();
		Appointment oldAppointment = appointmentRepo.findById(request.getOldAppointmentID()).get();

		oldAppointment.setMedicalService(null);
		oldAppointment.setPatient(null);
		oldAppointment.setStatus(AppointmentStatus.EMPTY);
		oldAppointment.setTaxCode(null);
		DoctorRequestAppointmentDTO patientRequestAppointment = new DoctorRequestAppointmentDTO();
		patientRequestAppointment.setAppointmentID(request.getNewAppointmentID());
		patientRequestAppointment.setPatientID(patient.getPatientID());
		return createAppointment(id, patientRequestAppointment);
	}


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


	public ResponseForLists<PatientResponseDTO> getMyPatients(long idDoc, int page, int size) {
		Page<Patient>  doctors = appointmentRepo.getAllMyPatients(idDoc, PageRequest.of(page, size));

		ResponseForLists<PatientResponseDTO> response = new ResponseForLists<>(mapper.mapFromIterableToPatientResponse(doctors.toList()));
		response.setCurrentPage(doctors.getNumber());
		response.setNumOfPages(doctors.getTotalPages());
		response.setNumOfElements(doctors.getNumberOfElements());
		response.setTotalElements(doctors.getTotalElements());
		return response;
	}

}
