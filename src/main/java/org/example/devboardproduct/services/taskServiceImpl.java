package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.TaskPriority;
import org.example.devboardproduct.entities.TaskStatus;
import org.example.devboardproduct.entities.Tasks;
import org.example.devboardproduct.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class taskServiceImpl implements taskService{

    @Autowired
    TaskRepository taskRepository;


    @Override
    public List<Tasks> getTasksByProjectId(UUID projectId, TaskStatus status, TaskPriority priority) {
        return taskRepository.findTasksWithFilters(projectId, status, priority);
    }
}
