package com.example.demo.api.noAuth;


import com.example.demo.model.entities.Doctor;
import com.example.demo.model.service.DoctorServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
public class CommonController {
    private final DoctorServiceImp doctorServiceImp;
    @GetMapping("getDoctors")
    public ResponseEntity<List<Doctor>> getDoctor() {
        return new ResponseEntity<>( doctorServiceImp.getDoctors(), HttpStatus.OK);
    }
}
