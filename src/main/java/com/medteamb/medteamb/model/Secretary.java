package com.medteamb.medteamb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //annotazione per dire a spring "questa è una classe tabella"
@Table(name = "secretary") //generare tabella e nome della tabella
@Data //generare getter e setter, to string, hash, equals
@AllArgsConstructor //costruttore popolato
@NoArgsConstructor //costruttore vuoto
@Builder //genera tutto il codice builder
public class Secretary {
	@Id //ID numero unico per ogni elemento
	@GeneratedValue(strategy = GenerationType.IDENTITY) /*istruzioni per generare Id*/

	private Integer secretaryId;
	private String secretaryName;
	private String secretarySurname;
	private String secretaryPhoneNumber;
	private String secretaryEmail;

}