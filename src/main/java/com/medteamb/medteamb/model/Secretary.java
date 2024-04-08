package com.medteamb.medteamb.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Secretary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer secretaryID;





	public Integer getSecretaryID() {
		return secretaryID;
	}

	public void setSecretaryID(Integer secretaryID) {
		this.secretaryID = secretaryID;
	}
}
