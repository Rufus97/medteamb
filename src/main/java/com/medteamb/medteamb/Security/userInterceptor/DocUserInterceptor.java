package com.medteamb.medteamb.Security.userInterceptor;

import com.medteamb.medteamb.Security.utils.Constants;
import com.medteamb.medteamb.Security.utils.JwtUtils;
import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.repository.patient.PatientRepository;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.NotFound;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// docID
@Component
public class DocUserInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authentication");

        if (token != null && request.getRequestURI().contains("/api/v1/doctor")){
            String authToken = request.getHeader("Authentication");
            Claims claim = jwtUtils.extractAllClaims(authToken);
            Long docID = Integer.toUnsignedLong((Integer) claim.get("id"));
            Doctor doctor = doctorRepository.findDoctorByUserId(docID).orElseThrow(
                    ()-> new NotFound("doctor not found")
            );
            System.out.println(doctor.getDoctorID());
            request.setAttribute(Constants.getDocIDattr(), doctor.getDoctorID());
            return true;
        }
        return true;
    }
}
