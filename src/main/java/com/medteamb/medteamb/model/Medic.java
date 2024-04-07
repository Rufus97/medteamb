package com.medteamb.medteamb.model;

import java.util.Objects;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Medic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicID;

	public Integer getMedicID() {
		return medicID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(medicID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medic other = (Medic) obj;
		return Objects.equals(medicID, other.medicID);
	}
	
}
