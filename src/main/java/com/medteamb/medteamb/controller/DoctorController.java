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
import com.medteamb.medteamb.service.dto.doctor.DoctorMapper;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;
import com.medteamb.medteamb.service.dto.doctor.DoctorResponseDTO;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

	private DoctorMapper doctorMapper;
	private DoctorService docService;

	public DoctorController(DoctorService docService, DoctorMapper doctorMapper) {
		this.doctorMapper = doctorMapper;
		this.docService = docService;
	}
	
	@PostMapping(path = "/create")
	public ResponseEntity<DoctorRequestDTO> createDoctor(@RequestBody DoctorRequestDTO doctorDto) {
		return new ResponseEntity<>(docService.saveDoctor(doctorDto), HttpStatus.CREATED) ;
	}
	
	@GetMapping(path ="/getAll")
	public List<DoctorRequestDTO> showAllDoctors(){
		return docService.showAllDocs();
	}
	
	@GetMapping(path ="/get/{doctorId}")
	public ResponseEntity<DoctorRequestDTO>showById(@PathVariable Integer doctorId){
		Optional<DoctorRequestDTO> foundDoctor = docService.findDocById(doctorId);
		if(foundDoctor.isPresent()) {
			DoctorRequestDTO doctorDto = foundDoctor.get();
			return new ResponseEntity<>(doctorDto, 
					HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "/update/{doctorId}")
	public DoctorResponseDTO updateById(@PathVariable Integer doctorId,
			@RequestBody DoctorRequestDTO doctorDto) {
		if(!docService.exists(doctorId)) {
			return null;
		}
		doctorDto.setDoctorID(doctorId);
		return docService.updateDoctorById(doctorMapper.mapFrom(doctorDto), doctorId);
	}
	
	@DeleteMapping(path = "/delete/{doctorId}")
	public void deleteDoctor(@PathVariable Integer doctorId){
		docService.deleteDoc(doctorId);
	}
}
