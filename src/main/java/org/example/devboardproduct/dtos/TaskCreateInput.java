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
public class TaskCreateInput {
    String Title;
    TaskStatus status;
    TaskPriority priority;
    UUID ProjectId;
    int AssigneeId = 0;
}
