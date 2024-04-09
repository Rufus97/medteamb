package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
}
