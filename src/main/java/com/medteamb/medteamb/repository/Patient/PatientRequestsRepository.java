package com.medteamb.medteamb.repository.Patient;

import com.medteamb.medteamb.model.Patient.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface PatientRequestsRepository extends JpaRepository<Requests, Integer> {

    Optional<Requests> findByDayAndHour(LocalDate date, LocalTime hour);


    Optional<Requests> findByday(LocalDate day);
}