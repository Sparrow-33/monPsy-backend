package com.example.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
        private Long id;
        private LocalTime startTime;
        private LocalTime endTime;
        private boolean status;
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "doctor_id", referencedColumnName = "id")
        private Doctor doctor;
        private LocalDate date;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;

    }


