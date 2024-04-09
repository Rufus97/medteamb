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

	@Override
	public int hashCode() {
		return Objects.hash(secretaryID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Secretary other = (Secretary) obj;
		return Objects.equals(secretaryID, other.secretaryID);
	}
	
}
