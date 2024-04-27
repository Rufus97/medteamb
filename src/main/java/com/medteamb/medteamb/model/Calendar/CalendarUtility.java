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
import java.util.Optional;


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
            initializeHours(initialDate, docID);
            initialDate = initialDate.plusDays(1);
        }
    }

    public void initializeHours(LocalDate today, Doctor doctor_id){
        LocalTime hourSlot = LocalTime.of(10,0);
        for (int i = 1; i <= 8*2; i++){
            AppointmentSlot slot = new AppointmentSlot(today, hourSlot,doctor_id);
            calendarRepo.save(slot);
            hourSlot = hourSlot.plusMinutes(30);
        }
    }
    @Scheduled(fixedRate = 2000L)
    public void getAllNoAgends(){

        Iterable<Integer> medicsNoAgend = calendarRepo.getAllDcotorWhoDontHaveAgenda();
         medicsNoAgend.forEach(
                 id -> {
                    initializeMonth(LocalDate.now(), LocalDate.now().plusMonths(1), doctorRepository.findById(id).get());
                 }
         );
         List<Integer> docsWithAgenda = new ArrayList<>();


         calendarRepo.getAllDocsWithAgenda().forEach(docsWithAgenda::add);
         docsWithAgenda.forEach( id ->
                updateAgendas(calendarRepo.updateDoc(id)));
    }

    public void updateAgendas(Optional<AppointmentSlot> appointments){
        LocalDate initialDate = appointments.get().getToday().plusMonths(1);
        LocalDate finalDate = LocalDate.now().plusMonths(1);
         if (initialDate.isBefore(finalDate)){
             System.out.println("sto aggiornando l'agenda da: " + appointments.get().getToday() + " a " + finalDate);
             initializeMonth(initialDate, finalDate, appointments.get().getDoctor_id());
         }
    }
}
