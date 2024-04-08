package com.medteamb.medteamb.model;

import java.util.Objects;

import jakarta.persistence.*;
@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientID;
	
	private String username;
	private String surname;
	private String phoneNumber;
	private String email;

	
}
