package com.example.demo.service;

import com.example.demo.entities.AppUser;
import com.example.demo.entities.Role;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService{

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("saving new user");
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role");
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role to a  user");
        AppUser appUser = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("getting a user by username");
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("getting list of users");
        return userRepo.findAll();
    }

    @Override
    public AppUser findUserByEmail(String email) {
        return userRepo.findAppUserByEmail(email);
    }




}
