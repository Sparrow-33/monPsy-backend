package com.example.demo;

import com.example.demo.model.entities.AppUser;
import com.example.demo.model.entities.Role;
import com.example.demo.model.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class MonPsyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonPsyApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
//            userService.saveRole(new Role(null, "PATIENT"));
//            userService.saveRole(new Role(null, "DOCTOR"));
//            userService.saveRole(new Role(null, "ADMIN"));
//            userService.saveRole(new Role(null, "SECRETARY"));

//            userService.saveUser
//                    (new AppUser(null, "admin","admin@monpsy", "user1UserName", new BCryptPasswordEncoder().encode("123456"), new ArrayList<>()));

            userService.saveUser(
                     AppUser.
                     builder().name("adminName")
                             .username("admin")
                             .email("admin@monpsy.ma").password(new BCryptPasswordEncoder().encode("123456"))
                             .roles(new ArrayList<>())
                             .isEnabled(false)
                    .build()
            );
//
            userService.addRoleToUser("admin", "ADMIN");

        };
    }
}
