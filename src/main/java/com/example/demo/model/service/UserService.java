package com.example.demo.model.service;

import com.example.demo.model.entities.AppUser;
import com.example.demo.model.entities.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(long id, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
    AppUser findUserByEmail(String email);
    String signUp(AppUser user, String role);

}
