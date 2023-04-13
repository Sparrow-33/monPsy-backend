package com.example.demo.model.service;


import com.example.demo.model.entities.Appointment;
import com.example.demo.model.repo.AppointmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;

    public Appointment newAppointment(Appointment appointment) {
           return appointmentRepo.save(appointment);
    }
}
