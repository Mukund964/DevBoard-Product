package org.example.devboardproduct.dtos;

import org.example.devboardproduct.entities.TaskPriority;
import org.example.devboardproduct.entities.TaskStatus;

import java.util.UUID;

public class TaskFilters {
    UUID projectId;
    TaskStatus status;
    TaskPriority priority;
    int assigneeId;
    int limit = 20;
    int offset = 0;
}
