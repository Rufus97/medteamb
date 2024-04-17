package com.medteamb.medteamb.model.Calendar;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.repository.CalendarRepo;
import jakarta.persistence.*;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Entity
public class SingleAgenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_agend;

    @OneToOne
    private Doctor doctor_id;

    private static CalendarRepo calendarRepo;
    public SingleAgenda(Doctor doctor_id){
        this.doctor_id = doctor_id;

        LocalDate initialDate = LocalDate.now();
        LocalDate finalDate = initialDate.plusMonths(6);
        while(initialDate.isBefore(finalDate)){
            SingleDay newDay = new SingleDay( initialDate, this, calendarRepo);
            initialDate = initialDate.plusDays(1);
        }
    }

    public Doctor getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Doctor doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getId_agend() {
         return id_agend;
     }

     public void setId_agend(Integer id_agend) {
         this.id_agend = id_agend;
     }
 }
