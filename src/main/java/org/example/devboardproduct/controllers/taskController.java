package org.example.devboardproduct.controllers;

import org.example.devboardproduct.dtos.TaskCreateInput;
import org.example.devboardproduct.dtos.TaskFilters;
import org.example.devboardproduct.dtos.UpdateTaskInput;
import org.example.devboardproduct.entities.TaskPriority;
import org.example.devboardproduct.entities.TaskStatus;
import org.example.devboardproduct.entities.Tasks;
import org.example.devboardproduct.services.taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class taskController {

    @Autowired
    taskService taskService;

    @MutationMapping
    public Tasks CreateTask(@Argument TaskCreateInput input){
        if(input.getPriority() == null) input.setPriority(TaskPriority.MEDIUM);
        if(input.getStatus() == null) input.setStatus(TaskStatus.TODO);
        return taskService.CreateTask(input);
    }

    @MutationMapping
    public Tasks UpdateTask(@Argument UpdateTaskInput input) {
        return taskService.updateTask(input);
    }

    @QueryMapping
    public List<Tasks> getTaskWithFilters(@Argument TaskFilters filters){
        if(filters == null) filters = new TaskFilters();
        return taskService.getTasksByFilters(filters);};
}
