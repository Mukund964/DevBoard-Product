package org.example.devboardproduct.services;

import org.example.devboardproduct.dtos.TaskCreateInput;
import org.example.devboardproduct.entities.*;
import org.example.devboardproduct.repository.ProjectRepository;
import org.example.devboardproduct.repository.TaskRepository;
import org.example.devboardproduct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class taskServiceImpl implements taskService{

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public List<Tasks> getTasksByProjectId(UUID projectId, TaskStatus status, TaskPriority priority) {
        return taskRepository.findTasksWithFilters(projectId, status, priority);
    }

    @Override
    public Tasks CreateTask(TaskCreateInput input) {

        Project projectToAdd = projectRepository.findById(input.getProjectId())
                .orElseThrow(()-> new RuntimeException("Project Id Doesn't exist"));

        User assignedUser = null;
        if(input.getAssigneeId() != 0){
        assignedUser = userRepository.findById(input.getAssigneeId())
                .orElseThrow(()-> new RuntimeException("assignee with provided id doesn't exists"));
        }

        Tasks newTask = Tasks.builder()
                .assignee(assignedUser)
                .Priority(input.getPriority())
                .Description("Dummy Task Description")
                .Status(input.getStatus())
                .title(input.getTitle())
                .project(projectToAdd)
                .build();

        return taskRepository.save(newTask);
    }
}
