package com.medteamb.medteamb.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "secretary")
public class Secretary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer secretaryId;
	
	@Column(nullable = false)
	private String secretaryName;
	
	@Column(nullable = false)
	private String secretarySurname;
	
	@Column(nullable = false)
	private String secretaryPhoneNumber;
	
	@Column(nullable = false, unique = false)
	private String secretaryEmail;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="appointmentID")
	private Appointment appointments;

}