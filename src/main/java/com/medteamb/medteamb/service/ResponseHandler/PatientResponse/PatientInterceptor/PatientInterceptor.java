package com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientInterceptor;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.medteamb.medteamb.repository.patient.PatientRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PatientInterceptor implements HandlerInterceptor {
    @Autowired
    private PatientRepository repo;

    JsonMapper builder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
