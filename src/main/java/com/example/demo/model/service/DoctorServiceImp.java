package com.example.demo.model.service;

import com.example.demo.model.entities.Doctor;
import com.example.demo.model.entities.Role;
import com.example.demo.model.repo.DoctorRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j


public class DoctorServiceImp implements DoctorService{

    private final DoctorRepo doctor;
    private final ConfirmationTokenService tokenService;

    @Override
    public Doctor saveDoctor(Doctor user) {
        return doctor.save(user);
    }

    @Override
    public Doctor getDoctor(String name) {
        return doctor.findDoctorByFamilyName(name);
    }

    public Long getDoctorIdFromEmail(String email) {
        return doctor.getDoctorByEmail(email).getId();
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctor.findAll();
    }

    @Override
    public Doctor findDoctorByEmail(String email) {
        return doctor.getDoctorByEmail(email);
    }

    @Override
    public String signUp(Doctor user) {
        Optional<Doctor> optionalDoctor = doctor.findDoctorByEmail(user.getEmail());
        if ( optionalDoctor.isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        doctor.save(user);
        return null;
    }
}
