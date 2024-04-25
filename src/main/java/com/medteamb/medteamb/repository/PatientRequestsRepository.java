package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Requests;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PatientRequestsRepository extends JpaRepository<Requests, Integer> {

    Optional<Requests> findByday(LocalDate date);

}