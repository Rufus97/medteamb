package com.medteamb.medteamb.service;

import com.medteamb.medteamb.Security.UserServiceImpl;
import com.medteamb.medteamb.model.UserEntity;
import com.medteamb.medteamb.service.dto.doctor.RegisterDoctorDTO;
import com.medteamb.medteamb.service.dto.patient.RegisterPatientDTO;
import com.medteamb.medteamb.repository.UserEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserEntityService {
    @Autowired
    UserEntityRepo userEntityRepo;

    @Autowired
    UserServiceImpl userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserEntity registerPatient(RegisterPatientDTO request) throws AuthenticationException {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles("PATIENT");
        return userEntityRepo.save(user);
    }

    public UserEntity registerDoctor(RegisterDoctorDTO request) throws AuthenticationException {
        if (request.getSpecialToken().equals("specialDoctorToken")){
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles("DOCTOR");
        return userEntityRepo.save(user);
        } else
        {
            throw new AuthenticationException("missing special token for doctor account");
        }
    }
}
