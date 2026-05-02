package org.example.devboardproduct.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String body;

    private User author;
    private Tasks tasks;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
