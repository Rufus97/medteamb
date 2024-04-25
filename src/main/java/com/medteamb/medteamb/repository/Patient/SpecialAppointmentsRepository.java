package com.medteamb.medteamb.repository.Patient;

import com.medteamb.medteamb.model.Patient.SpecialAppointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SpecialAppointmentsRepository extends JpaRepository<SpecialAppointments, Long> {

    Optional<SpecialAppointments> findByappointmentDate(LocalDate date);
}
