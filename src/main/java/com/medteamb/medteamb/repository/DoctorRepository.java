package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.agenda.Appointment;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{



}

