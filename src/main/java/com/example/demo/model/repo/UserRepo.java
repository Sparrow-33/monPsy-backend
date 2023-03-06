package com.example.demo.repo;

import com.example.demo.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    AppUser findAppUserByEmail(String email);
}
