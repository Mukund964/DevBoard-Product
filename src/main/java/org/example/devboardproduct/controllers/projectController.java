package org.example.devboardproduct.controllers;

import org.example.devboardproduct.entities.Project;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class projectController {


    @MutationMapping
    Project createProject(@Argument String name, @Argument int OwnerId){

    }

}
