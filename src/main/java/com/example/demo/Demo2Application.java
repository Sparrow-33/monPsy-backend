package com.example.demo;

import com.example.demo.entities.AppUser;
import com.example.demo.entities.Role;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
//    @Bean
//    CommandLineRunner runner(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_MANAGER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new AppUser(null, "user1","user@test.ma", "user1UserName", new BCryptPasswordEncoder().encode("123456"), new ArrayList<>()));
////            userService.saveUser(new AppUser(null, "user2", "user2UserName", "1234", new ArrayList<>()));
////            userService.saveUser(new AppUser(null, "user3", "user3UserName", "1234", new ArrayList<>()));
////            userService.saveUser(new AppUser(null, "user4", "user4UserName", "1234", new ArrayList<>()));
//
//            userService.addRoleToUser("user1UserName", "ROLE_USER");
////            userService.addRoleToUser("user2UserName", "ROLE_MANAGER");
////            userService.addRoleToUser("user3UserName", "ROLE_ADMIN");
////            userService.addRoleToUser("user4UserName", "ROLE_SUPER_ADMIN");
//
//        };
//    }
}
