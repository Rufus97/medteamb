package com.medteamb.medteamb.service.dto.doctor;

import com.medteamb.medteamb.model.Specialization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequestDTO {
	
	private Integer doctorID;
	private String doctorName;
	private String doctorSurname;
	private String doctorPhoneNumber;
	private String doctorEmail;
	private Specialization specialization;
	
}
