package com.medteamb.medteamb.repository;

import com.medteamb.medteamb.model.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary, Integer> {
    Secretary findBySecretaryEmail (String SecretaryEmail);
}
