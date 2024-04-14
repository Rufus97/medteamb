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

    private CalendarRepo calendar;
    private LocalDate today;

    public SingleDay(CalendarRepo calendar,  LocalDate today) {
        this.calendar = calendar;
        initializeHours(today);
    }
    @PostConstruct
    public void initializeHours( LocalDate today){
        LocalTime hourSlot = LocalTime.of(10,0);
        for (int i = 1; i <= 8*2; i++){
            AppointmentSlot slot = new AppointmentSlot(today, hourSlot, hourSlot.plusMinutes(30));
            calendar.save(slot);
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
