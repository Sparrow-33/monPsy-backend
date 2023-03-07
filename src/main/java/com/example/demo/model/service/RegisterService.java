package com.example.demo.model.service;

import com.example.demo.helper.EmailValidator;
import com.example.demo.model.dto.RegisterRequest;
import com.example.demo.model.entities.AppUser;
import lombok.AllArgsConstructor;
import org.hibernate.metamodel.mapping.ModelPart;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class RegisterService {
    private final EmailValidator emailValidator;
    private final ModelMapper mapper = new ModelMapper();
    private UserServiceImplementation userService;
    public String register(RegisterRequest request) {

        if (!emailValidator.test(request.getEmail())) {
            throw new IllegalStateException("email not valid");
        }
        AppUser user = mapper.map(request, AppUser.class);
        userService.signUp(user, "PATIENT");
        return "NEW USER REGISTERED";
    }

}
