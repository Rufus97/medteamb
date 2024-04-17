package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Calendar.SingleAgenda;
import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.AgendRepo;
import org.springframework.stereotype.Service;

@Service
public class AgendService {

    AgendRepo repo;

    public AgendService(AgendRepo repo) {
        this.repo = repo;
    }

    public SingleAgenda saveNewAgend(Doctor doc){
        return repo.save(new SingleAgenda(doc));
    }
}
