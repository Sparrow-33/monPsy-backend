package com.example.demo.config.usersDetails;

import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.model.entities.AppUser;
import com.example.demo.model.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("patientDetailsService")
public class PatientDetailsService implements UserDetailsService {

    private final UserRepo patientRepository;

    @Autowired
    @Lazy
    public PatientDetailsService(UserRepo patientRepository) {
        this.patientRepository = patientRepository;
    }

    public UserDetailsService patientUserDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                AppUser user = patientRepository.getAppUserByEmail(email);
                if (user == null) {
                    throw new InvalidCredentialsException("invalid credentials");
                }
                System.out.println(user);

                return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("PATIENT")));

            }
        };

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = patientRepository.getAppUserByEmail(email);
        if (user == null) {
            throw new InvalidCredentialsException("invalid credentials");
        }
        System.out.println("INSIDE PATIENT DETAILS SERVICE");
        System.out.println(user);
        return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("PATIENT")));

    }
}
