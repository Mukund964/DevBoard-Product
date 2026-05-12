package org.example.devboardproduct.controllers;

import org.example.devboardproduct.entities.Project;
import org.example.devboardproduct.services.projectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class projectController {

    @Autowired
    projectService projectService;

    @MutationMapping
    Project createProject(@Argument String name, @Argument int OwnerId){
        return projectService.createProject(name,OwnerId);
    }

    @MutationMapping
    Project addMemberToProject(@Argument UUID projectId, @Argument int memberId){
        return projectService.addMemberToProject(projectId,memberId);
    }

    @MutationMapping
    Project removeMemberToProject(@Argument UUID projectId, @Argument int memberId){
        return projectService.removeMemberToProject(projectId,memberId);
    }
}
