package org.example.devboardproduct.controllers;

import org.example.devboardproduct.entities.Project;
import org.example.devboardproduct.entities.TaskPriority;
import org.example.devboardproduct.entities.TaskStatus;
import org.example.devboardproduct.entities.Tasks;
import org.example.devboardproduct.services.projectService;
import org.example.devboardproduct.services.taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class projectController {

    @Autowired
    projectService projectService;

    @Autowired
    taskService taskService;

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


    @QueryMapping
    Project getProject(@Argument UUID projectId){
        return projectService.getProject(projectId);
    }

    @SchemaMapping(typeName ="Project",field = "tasks")
    List<Tasks> tasks(Project project, @Argument TaskStatus status, @Argument TaskPriority priority){
        return taskService.getTasksByProjectId(project.getId(),status,priority);
    }
 }
