package org.example.devboardproduct.controllers;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.example.devboardproduct.dtos.createUserInput;
import org.example.devboardproduct.entities.User;
import org.example.devboardproduct.entities.userRole;
import org.example.devboardproduct.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @QueryMapping
    public User getUser(@Argument int id){
        return userService.getUser(id);
    }

    @MutationMapping
    public User createUser(@Argument @Valid createUserInput input){
        String name = input.getName();
        String email = input.getEmail();

        // 2. Defaulting logic: If role is null, default to DEVELOPER
        userRole assignedRole = (input.getRole() != null) ? input.getRole() : userRole.DEVELOPER; //

        // 3. Return the object directly; Spring handles the HTTP 200 OK
        return userService.createUser(name, email, assignedRole);
    }
}
