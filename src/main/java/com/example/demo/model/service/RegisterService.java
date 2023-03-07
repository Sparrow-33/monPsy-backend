package com.example.demo.model.service;

import com.example.demo.helper.EmailValidator;
import com.example.demo.model.dto.RegisterRequest;
import com.example.demo.model.entities.AppUser;
import com.example.demo.model.entities.ConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.metamodel.mapping.ModelPart;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
@Slf4j
public class RegisterService {
    private final EmailValidator emailValidator;
    private final ModelMapper mapper = new ModelMapper();
    private UserServiceImplementation userService;
    private  ConfirmationTokenService confirmationTokenService;
    private final UserServiceImplementation userServiceImplementation;
    public String register(RegisterRequest request) {

        if (!emailValidator.test(request.getEmail())) {
            throw new IllegalStateException("email not valid");
        }
        AppUser user = mapper.map(request, AppUser.class);
        log.info("NEW USER REGISTERED");
       return userService.signUp(user, "PATIENT");
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow( () ->
                   new IllegalStateException("token not found")
                );
        if (confirmationToken.getConfirmedAt() != null) {
                throw new IllegalStateException("email already confirmed");
        }
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
               throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userServiceImplementation.enableAppUser(
                confirmationToken.getUser().getEmail()
        );

        return "confirmed";
    }



}
