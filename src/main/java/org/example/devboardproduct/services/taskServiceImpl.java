package org.example.devboardproduct.services;

import jakarta.transaction.Transactional;
import org.example.devboardproduct.dtos.TaskCreateInput;
import org.example.devboardproduct.dtos.TaskFilters;
import org.example.devboardproduct.dtos.UpdateTaskInput;
import org.example.devboardproduct.entities.*;
import org.example.devboardproduct.filters.TaskSpecification;
import org.example.devboardproduct.repository.ProjectRepository;
import org.example.devboardproduct.repository.TaskRepository;
import org.example.devboardproduct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public Tasks updateTask(UpdateTaskInput input) {
        // 1. Fetch the existing record
        Tasks task = taskRepository.findById(input.getId())
                .orElseThrow(()-> new RuntimeException("Task Not Found"));


        // 2. Apply ONLY provided fields (The Null-Check Pattern)
        if (input.getTitle() != null) task.setTitle(input.getTitle());
        if (input.getDescription() != null) task.setDescription(input.getDescription());
        if (input.getPriority() != null) task.setPriority(input.getPriority());


        if (input.getStatus() != null) {
            task.setStatus(input.getStatus());

            if (input.getStatus() == TaskStatus.DONE) {
                task.setUpdatedAt(LocalDateTime.now());
            }
        }


        if (input.getAssigneeId() != null) {
            User user = userRepository.findById(input.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException("Assignee not found"));
            task.setAssignee(user);
        }

        // Hibernate detects the changes and syncs them automatically at the end of the @Transactional method
        return taskRepository.save(task);
    }

    @Override
    public List<Tasks> getTasksByFilters(TaskFilters filters) {
        Specification<Tasks> spec =
                TaskSpecification.withFilters(filters);

        PageRequest page = PageRequest.of(
                filters.getOffset() / filters.getLimit(),
                filters.getLimit()
        );
        return taskRepository
                .findAll(spec, page)
                .getContent();
    }
}
