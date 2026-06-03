package org.example.devboardproduct.dtos;

import lombok.*;
import org.example.devboardproduct.entities.TaskPriority;
import org.example.devboardproduct.entities.TaskStatus;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskInput {
    private UUID id; // Mandatory to locate the task
    private String title; // Optional
    private String description; // Optional
    private TaskStatus status; // Optional
    private TaskPriority priority; // Optional
    private Integer assigneeId; // Optional (Integer wrapper allows null)
}