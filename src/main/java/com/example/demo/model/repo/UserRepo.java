package com.example.demo.model.repo;

import com.example.demo.model.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    AppUser findAppUserByEmail(String email);
}
