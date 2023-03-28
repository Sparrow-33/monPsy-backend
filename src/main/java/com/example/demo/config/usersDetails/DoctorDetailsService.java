package com.example.demo.config.usersDetails;

import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.model.entities.Doctor;
import com.example.demo.model.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("doctorDetailsService")
public class DoctorDetailsService implements UserDetailsService {

    private final DoctorRepo doctorRepo;

    @Autowired
    @Lazy
    public DoctorDetailsService(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public UserDetailsService doctorDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Doctor user = doctorRepo.getDoctorByEmail(email);
                if (user == null) {
                    throw new InvalidCredentialsException("invalid credentials");
                }
                System.out.println(user);

                return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("DOCTOR")));

            }
        };

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("IN DOC DETAILS SERVICE");
        Doctor patient = doctorRepo.findDoctorByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(patient.getEmail(), patient.getPassword(), Collections.singleton(new SimpleGrantedAuthority("DOCTOR")));
    }
}
