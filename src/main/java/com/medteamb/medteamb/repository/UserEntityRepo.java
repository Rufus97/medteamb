package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
