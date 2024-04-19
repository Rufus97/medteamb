package com.medteamb.medteamb.model.Calendar;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.CalendarRepo;
import com.medteamb.medteamb.repository.DoctorRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Component
@EnableScheduling
public class CalendarUtility {

    CalendarRepo calendarRepo;

    DoctorRepository doctorRepository;


    public CalendarUtility(CalendarRepo calendarRepo, DoctorRepository doctorRepository) {
        this.calendarRepo = calendarRepo;
        this.doctorRepository = doctorRepository;
    }

    public void initializeMonth(LocalDate initialDate, LocalDate finalDate, Doctor docID){
        while(initialDate.isBefore(finalDate)){
            System.out.println("faccio un giro" + initialDate + " " + finalDate);
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
                    initializeMonth(LocalDate.now(), LocalDate.now().plusMonths(1), doctorRepository.findById(id).get());
                 }
         );
         List<Integer> docsWithAgenda = new ArrayList<>();
         calendarRepo.getAllDocsWithAgenda().forEach(docsWithAgenda::add);
         updateAgenda(calendarRepo.getDocLastSlot(docsWithAgenda.size()));

    }

    public void updateAgenda(Iterable<AppointmentSlot> slots){


        for (AppointmentSlot slot : slots){
            if (!slot.getToday().plusMonths(1).equals(slot.getToday().plusMonths(1))){
                initializeMonth(LocalDate.now(), LocalDate.now().plusMonths(1), slot.getDoctor_id());
            }
        }
    }



}
