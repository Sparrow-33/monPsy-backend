package com.example.demo.service;

import com.example.demo.entities.AppUser;
import com.example.demo.entities.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();


    AppUser findUserByEmail(String email);

}
