package com.medteamb.medteamb.model.Calendar;

import com.medteamb.medteamb.repository.CalendarRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class SingleDay {


    CalendarRepo calendarRepo;
    private LocalDate today;

    public SingleDay(LocalDate today, SingleAgenda month, CalendarRepo calendarRepo) {
        this.calendarRepo = calendarRepo;
        initializeHours(today, month);
    }

    public void initializeHours(LocalDate today,  SingleAgenda agenda_id){
        LocalTime hourSlot = LocalTime.of(10,0);
        for (int i = 1; i <= 8*2; i++){
            AppointmentSlot slot = new AppointmentSlot(today, hourSlot, hourSlot.plusMinutes(30),  agenda_id);
            calendarRepo.save(slot);
            hourSlot = hourSlot.plusMinutes(30);
        }
    }


    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return " day: " + today.getDayOfWeek() + " " + today;
    }
}
