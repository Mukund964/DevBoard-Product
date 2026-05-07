package org.example.devboardproduct.controllers;

import org.apache.logging.log4j.message.Message;
import org.example.devboardproduct.entities.User;
import org.example.devboardproduct.entities.userRole;
import org.example.devboardproduct.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @MutationMapping
    public User CreateUser(@Argument String name, @Argument String email, @Argument userRole role){

        //for validation in graphql no need to return responseEntity just throw exception
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Please provide name");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Enter a valid email");
        }

        // 2. Defaulting logic: If role is null, default to DEVELOPER
        userRole assignedRole = (role != null) ? role : userRole.Developer; //

        // 3. Return the object directly; Spring handles the HTTP 200 OK
        return userService.createUser(name, email, assignedRole);
    }
}
