package com.medteamb.medteamb.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.NotFound;
import com.medteamb.medteamb.service.ResponseHandler.Response;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.DTOmapper;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.doctor.DoctorResponseDTO;
import com.medteamb.medteamb.service.dto.patient.PatientResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medteamb.medteamb.service.DoctorService;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;


@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

	private DoctorService docService;

	private DTOmapper mapper;

	public DoctorController(DoctorService docService, DTOmapper mapper) {
		this.docService = docService;
		this.mapper = mapper;
	}



	/*
▪
▪
▪ poter annotare le informazioni relative alla visita e la diagnosi
▪ poter prescrivere un’impegnativa per la visita dallo specialista
▪ poter prescrivere farmaci ai miei pazienti, specificando dosaggio e durata della terapia
▪ poter creare, modificare o cancellare gli appuntamenti dei miei pazienti
▪ poter consultare i referti caricati dei miei pazienti*/


	//poter visualizzare la mia agenda con gli appuntamenti della giornata e i dettagli dei pazienti
	@GetMapping("/myAppointments")
	public ResponseForLists<AppointmentResponseDTO> getMyAppointments(@RequestParam Long id, int page, int size){
		return docService.getMyAppointments(id, page, size);
	}

	//poter accedere alle informazioni sanitarie dei miei pazienti in vista della visita
    @GetMapping("/myPatients/{id}")
	public ResponseForLists<PatientResponseDTO> getMyPatients(@PathVariable long idDoc, int page, int size){
		return docService.getMyPatients(idDoc, page, size);
	}

	// poter creare e modificare i profili dei miei pazienti, inserendo dati anagrafici, recapiti e
	//	SOPRATTUTTO informazioni sanitarie e dettagli sull’esenzione del ticket //forse aggiornare model paziente




	@PostMapping(path = "/create")
	public Response<DoctorResponseDTO> createDoctor(@RequestBody DoctorRequestDTO doctorDto) {
		return docService.newDoctor(doctorDto);
	}
	
	@GetMapping(path ="/getAll/{page}")
	public ResponseForLists<DoctorResponseDTO> showAllDoctors(@PathVariable int page, @RequestParam int size){
		return docService.showAllDocs(page, size);
	}
	
	@GetMapping(path ="/get/{doctorId}")
	public ResponseEntity<DoctorResponseDTO>showById(@PathVariable Long doctorId){
		Optional<DoctorResponseDTO> foundDoctor = docService.findDocById(doctorId);
		if(foundDoctor.isPresent()) {
			DoctorResponseDTO doctorDto = foundDoctor.get();
			return new ResponseEntity<>(doctorDto, 
					HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "/update/{doctorId}")
	public Response<DoctorResponseDTO> updateById(@PathVariable Long doctorId,
			@RequestBody DoctorRequestDTO doctorDto) {
		if(!docService.exists(doctorId)) {
			throw new NotFound("doctor not found");
		}
        return createDoctor(doctorDto);
	}
	
	@DeleteMapping(path = "/delete/{doctorId}")
	public ResponseEntity<DoctorRequestDTO>deleteDoctor(@PathVariable Long doctorId){
		docService.deleteDoc(doctorId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
