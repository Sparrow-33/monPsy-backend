package com.example.demo.model.dto;

import com.example.demo.model.entities.AppUser;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    private static final ModelMapper mapper = new ModelMapper();

    @Test
    public void checkRegisterMapping() {
        RegisterRequest request = new RegisterRequest();
        request.setName("patient");
        request.setUsername("patient1");
        request.setEmail("patient1@monpsy.ma");
        request.setPassword("123456");

        AppUser patient = mapper.map(request, AppUser.class);

        assertEquals(request.getName(), patient.getName());
        assertEquals(request.getUsername(), patient.getUsername());
        assertEquals(request.getEmail(), patient.getEmail());
    }

}