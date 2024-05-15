package com.medteamb.medteamb.repository.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface SpecialAppointmentsRepository extends JpaRepository<SpecialAppointments, Long> {



    Optional<Requests> findByAppointmentDateAndAppointmentHour(LocalDate appointmentDate, LocalTime appointmentHour);
}
