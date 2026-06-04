package org.example.devboardproduct.dtos;

import lombok.*;
import org.example.devboardproduct.entities.TaskPriority;
import org.example.devboardproduct.entities.TaskStatus;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskFilters {
    UUID projectId;
    TaskStatus Status;
    TaskPriority Priority;
    Integer assigneeId;
    int limit = 20;
    int offset = 0;
}
