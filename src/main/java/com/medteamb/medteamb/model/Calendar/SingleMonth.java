package com.medteamb.medteamb.model.Calendar;

import com.medteamb.medteamb.repository.CalendarRepo;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
@Configuration
public class SingleMonth {

    CalendarRepo calendar;
    SingleMonth(CalendarRepo calendar){
        LocalDate initialDate = LocalDate.now();
        LocalDate finalDate = initialDate.plusMonths(6);
        while(initialDate.isBefore(finalDate)){
            SingleDay newDay = new SingleDay(calendar, initialDate);
            initialDate = initialDate.plusDays(1);
        }
    }
}
