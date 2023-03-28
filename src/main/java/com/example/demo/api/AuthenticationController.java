package com.example.demo.api;

import com.example.demo.config.JwtUtil;
import com.example.demo.config.usersDetails.DoctorDetailsService;
import com.example.demo.config.usersDetails.PatientDetailsService;
import com.example.demo.model.dto.AuthenticationRequest;
import com.example.demo.model.dto.RegisterRequest;
import com.example.demo.model.dto.TokenResponse;
import com.example.demo.model.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
//    @Qualifier("doctorDetailsService")
//    private final DoctorDetailsService doctorDetailsService;
//    @Qualifier("patientDetailsService")
//    private final PatientDetailsService patientDetailsService;
    private final JwtUtil jwtUtil;
    private final RegisterService registerService;

    @PostMapping("/authenticate")
    public ResponseEntity authenticate( @RequestBody AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail().trim()+"-PATIENT",
                        request.getPassword().trim())
        );

        final UserDetails user = userDetailsService.loadUserByUsername(request.getEmail()+"-PATIENT");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwtUtil.generateToken(user));
            return ResponseEntity.ok(tokenResponse);

    }

    @PostMapping("/doctorAuth")
    public ResponseEntity authenticateDoctor( @RequestBody AuthenticationRequest request) {
        System.out.println("DOCTOR AUTH");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail().trim()+"-DOCTOR", request.getPassword())
        );

        final UserDetails user = userDetailsService.loadUserByUsername(request.getEmail()+"-DOCTOR");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwtUtil.generateToken(user));

        return ResponseEntity.ok(tokenResponse);

    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return registerService.register(registerRequest);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerService.confirmToken(token);
    }
}
