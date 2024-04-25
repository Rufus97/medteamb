package com.medteamb.medteamb.repository.Patient;

import com.medteamb.medteamb.model.Patient.PatientRefert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefertRepository extends JpaRepository<PatientRefert, Long> {


    @Query(value = "select r.* from referto r join patient p " +
            "on p.patientid = r.patient_id " +
            "where p.patientid = :patientID", nativeQuery = true)
    Iterable<PatientRefert> getHistoryOfRefertsByPatientID(@Param("patientID") Long id);
}
