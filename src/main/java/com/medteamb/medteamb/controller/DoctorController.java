package com.medteamb.medteamb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medteamb.medteamb.service.DoctorService;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

	private DoctorService docService;

	public DoctorController(DoctorService docService) {
		this.docService = docService;
	}



	/*poter visualizzare la mia agenda con gli appuntamenti della giornata e i dettagli dei pazienti
▪ poter accedere alle informazioni sanitarie dei miei pazienti in vista della visita
▪ poter creare e modificare i profili dei miei pazienti, inserendo dati anagrafici, recapiti e
	SOPRATTUTTO informazioni sanitarie e dettagli sull’esenzione del ticket
▪ poter annotare le informazioni relative alla visita e la diagnosi
▪ poter prescrivere un’impegnativa per la visita dallo specialista
▪ poter prescrivere farmaci ai miei pazienti, specificando dosaggio e durata della terapia
▪ poter creare, modificare o cancellare gli appuntamenti dei miei pazienti
▪ poter consultare i referti caricati dei miei pazienti*/
	
	@PostMapping(path = "/create")
	public ResponseEntity<DoctorRequestDTO> createDoctor(@RequestBody DoctorRequestDTO doctorDto) {
		return new ResponseEntity<>(docService.saveDoctor(doctorDto), HttpStatus.CREATED) ;
	}
	
	@GetMapping(path ="/getAll")
	public List<DoctorRequestDTO> showAllDoctors(){
		return docService.showAllDocs();
	}
	
	@GetMapping(path ="/get/{doctorId}")
	public ResponseEntity<DoctorRequestDTO>showById(@PathVariable Long doctorId){
		Optional<DoctorRequestDTO> foundDoctor = docService.findDocById(doctorId);
		if(foundDoctor.isPresent()) {
			DoctorRequestDTO doctorDto = foundDoctor.get();
			return new ResponseEntity<>(doctorDto, 
					HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "/update/{doctorId}")
	public ResponseEntity<DoctorRequestDTO>updateById(@PathVariable Long doctorId,
			@RequestBody DoctorRequestDTO doctorDto) {
		if(!docService.exists(doctorId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		doctorDto.setDoctorID(doctorId);
		return new ResponseEntity<>(docService.saveDoctor(doctorDto),
				HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/delete/{doctorId}")
	public ResponseEntity<DoctorRequestDTO>deleteDoctor(@PathVariable Long doctorId){
		docService.deleteDoc(doctorId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
