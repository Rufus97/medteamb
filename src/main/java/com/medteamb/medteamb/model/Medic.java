package com.medteamb.medteamb.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Medic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicID;

	public Integer getMedicID() {
		return medicID;
	}

	public void setMedicID(Integer medicID) {
		this.medicID = medicID;
	}
}
