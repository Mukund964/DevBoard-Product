package org.example.devboardproduct.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Comments {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id")
    private Tasks task;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
