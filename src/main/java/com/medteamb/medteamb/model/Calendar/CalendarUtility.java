package com.medteamb.medteamb.model.Calendar;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.CalendarRepo;
import com.medteamb.medteamb.repository.DoctorRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;


@Component
@EnableScheduling
public class CalendarUtility {

    CalendarRepo calendarRepo;

    DoctorRepository doctorRepository;


    public CalendarUtility(CalendarRepo calendarRepo, DoctorRepository doctorRepository) {
        this.calendarRepo = calendarRepo;
        this.doctorRepository = doctorRepository;
    }

    public void initializeMonth(Doctor docID){
        LocalDate initialDate = LocalDate.now();
        LocalDate finalDate = initialDate.plusMonths(1);
        while(initialDate.isBefore(finalDate)){
            initializeHours(initialDate, docID);
            initialDate = initialDate.plusDays(1);
        }
    }

    public void initializeHours(LocalDate today, Doctor doctor_id){
        LocalTime hourSlot = LocalTime.of(10,0);
        for (int i = 1; i <= 8*2; i++){
            AppointmentSlot slot = new AppointmentSlot(today, hourSlot, hourSlot.plusMinutes(30), doctor_id);
            calendarRepo.save(slot);
            hourSlot = hourSlot.plusMinutes(30);
        }
    }
    @Scheduled(fixedRate = 2000L)
    public void getAllNoAgends(){
        Iterable<Integer> medicsNoAgend = calendarRepo.getAllDcotorWhoDontHaveAgenda();
        System.out.println(medicsNoAgend + " sta girando da solo !!!");
         medicsNoAgend.forEach(
                 id -> {
                    initializeMonth(doctorRepository.findById(id).get());
                 }
         );

        System.out.println(calendarRepo.getDocLastSlot(2));

    }

    public void checkDocAgends(){

    }


}
