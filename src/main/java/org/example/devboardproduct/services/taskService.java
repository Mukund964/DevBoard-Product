package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.TaskPriority;
import org.example.devboardproduct.entities.TaskStatus;
import org.example.devboardproduct.entities.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface taskService {
    List<Tasks> getTasksByProjectId(UUID projectId, TaskStatus status, TaskPriority priority);
}
