package com.example.demo.model.service;

import com.example.demo.model.entities.Doctor;
import com.example.demo.model.entities.Role;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {

    Doctor saveDoctor(Doctor user);
    Doctor getDoctor(String username);
    List<Doctor> getDoctors();
    Doctor findDoctorByEmail(String email);
    String signUp(Doctor user);
}
