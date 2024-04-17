package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Calendar.SingleAgenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendRepo extends JpaRepository<SingleAgenda, Integer> {
}
