package com.medteamb.medteamb.repository.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medteamb.medteamb.model.patient.PatientRefert;

public interface RefertRepository extends JpaRepository<PatientRefert, Long> {


    @Query(value = "select r.* from referto r join patient p " +
            "on p.patientid = r.patient_id " +
            "where p.patientid = :patientID", nativeQuery = true
    ,countQuery = "select count(r.id) from referto r join patient p " +
            " on p.patientid = r.patient_id "+
            " where p.patientid = :patientID ")
    Page<PatientRefert> getHistoryOfRefertsByPatientID(@Param("patientID") Long id, PageRequest page);
}
