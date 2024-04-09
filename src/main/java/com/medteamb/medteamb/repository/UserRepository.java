package com.medteamb.medteamb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medteamb.medteamb.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>{
}
