package com.medteamb.medteamb.Security.userInterceptor;

import com.medteamb.medteamb.Security.utils.JwtUtils;
import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.repository.patient.PatientRepository;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.NotFound;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authentication");

        if (token != null && request.getRequestURI().contains("/api/v1/patients")){
            String authToken = request.getHeader("Authentication");
            Claims claim = jwtUtils.extractAllClaims(authToken);
            Long patId = Integer.toUnsignedLong((Integer) claim.get("id"));
            Patient patient = patientRepository.findPatientByUserId(patId).orElseThrow(
                    ()-> new NotFound("patient not found")
            );
            request.setAttribute("id", patient.getPatientID());
            return true;
        }
        return true;
    }
}
