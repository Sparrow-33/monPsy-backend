package com.example.demo.model.repo;

import com.example.demo.model.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
