package com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientInterceptor;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.spi.ObjectFactoryBuilder;

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
