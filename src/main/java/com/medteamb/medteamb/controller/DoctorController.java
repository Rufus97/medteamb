package com.medteamb.medteamb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medteamb.medteamb.service.DoctorService;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;
import com.medteamb.medteamb.service.dto.doctor.DoctorResponseDTO;

import it.pasqualecavallo.studentsmaterial.authorization_framework.filter.AuthenticationContext;
import it.pasqualecavallo.studentsmaterial.authorization_framework.filter.AuthenticationContext.Principal;
import it.pasqualecavallo.studentsmaterial.authorization_framework.security.RoleSecurity;


@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

	private DoctorService docService;

	public DoctorController(DoctorService docService) {
		this.docService = docService;
	}
	
	@RoleSecurity("ROLE_ADMIN")
	@PostMapping(path = "/create")
	public DoctorResponseDTO createDoctor(@RequestBody DoctorRequestDTO doctorDto) {
		Principal principal = AuthenticationContext.get();
		principal.getUsername();
		return docService.saveDoctor(doctorDto) ;
	}
	
	@RoleSecurity("ROLE_ADMIN")
	@GetMapping(path ="/getAll")
	public List<DoctorResponseDTO> showAllDoctors(){
		return docService.showAllDocs();
	}
	
	@RoleSecurity("ROLE_ADMIN")
	@GetMapping(path ="/get/{doctorID}")
	public DoctorResponseDTO showById(@PathVariable Integer doctorID){
		return docService.findDocById(doctorID);
	}
	
	@RoleSecurity("ROLE_ADMIN")
	@PutMapping(path = "/update/{doctorID}")
	public DoctorResponseDTO updateById(@PathVariable Integer doctorID,
			@RequestBody DoctorRequestDTO doctorDto) {
		return docService.updateDoctor(doctorDto, doctorID);
	}
	
	@RoleSecurity("ROLE_ADMIN")
	@DeleteMapping(path = "/delete/{doctorID}")
	public void deleteDoctor(@PathVariable Integer doctorID){
		docService.deleteDoc(doctorID);
	}
}
