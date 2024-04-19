package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public Doctor saveCoc(Doctor newDoctor){
        return repo.save(newDoctor);
    }
}
