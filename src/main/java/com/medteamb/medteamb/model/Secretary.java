package com.medteamb.medteamb.model;

import jakarta.persistence.*;


import java.util.Objects;
@Entity
@Table(name = "secretary")
public class Secretary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long secretaryID;
	@Column(nullable = false)
	private String secretaryName;
	@Column(nullable = false)
	private String secretarySurname;
	@Column(nullable = false)
	private String secretaryPhoneNumber;
	@Column(nullable = false, unique = true)
	private String secretaryEmail;
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy="appointmentID")
	//private Appointment appointments;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;


//	@OneToOne
//	@JoinColumn(name = "user")
//	private User user;

	public Long getSecretaryID() {
		return secretaryID;
	}

	public String getSecretaryName() {
		return secretaryName;
	}

	public String getSecretarySurname() {
		return secretarySurname;
	}

	public String getSecretaryPhoneNumber() {
		return secretaryPhoneNumber;
	}

	public String getSecretaryEmail() {
		return secretaryEmail;
	}

	public void setSecretaryName(String secretaryName) {
		this.secretaryName = secretaryName;
	}

	public void setSecretarySurname(String secretarySurname) {
		this.secretarySurname = secretarySurname;
	}

	public void setSecretaryPhoneNumber(String secretaryPhoneNumber) {
		this.secretaryPhoneNumber = secretaryPhoneNumber;
	}

	public void setSecretaryEmail(String secretaryEmail) {
		this.secretaryEmail = secretaryEmail;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Secretary secretary)) return false;
        return Objects.equals(getSecretaryID(), secretary.getSecretaryID()) && Objects.equals(getSecretaryName(), secretary.getSecretaryName()) && Objects.equals(getSecretarySurname(), secretary.getSecretarySurname()) && Objects.equals(getSecretaryPhoneNumber(), secretary.getSecretaryPhoneNumber()) && Objects.equals(getSecretaryEmail(), secretary.getSecretaryEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSecretaryID(), getSecretaryName(), getSecretarySurname(), getSecretaryPhoneNumber(), getSecretaryEmail());
	}

}