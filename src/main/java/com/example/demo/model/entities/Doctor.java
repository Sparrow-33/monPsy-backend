package com.example.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String personaName;
    private String familyName;
    private String email;
    private String password;
    private String about;
    private String address;
    private Boolean isEnabled = false;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;



}