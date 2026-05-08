package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.User;
import org.example.devboardproduct.entities.userRole;

import java.util.Optional;


public interface UserService {
    User createUser(String name, String email, userRole role);
    User getUser(int id);
}
