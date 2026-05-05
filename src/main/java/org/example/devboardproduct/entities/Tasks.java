package org.example.devboardproduct.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID Id;

    @Column(nullable = false, length = 200)
    private String title;

    private String Description;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("TODO")
    private TaskStatus TaskStatus;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("HIGH")
    private TaskPriority TaskPriority;


    @ManyToOne
    @JoinColumn(name = "assignee_id", nullable = true)
    private User assignee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC") // Keeps comments in chronological order
    private List<Comments> comments = new ArrayList<>();

    private LocalDateTime DueDate;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime CreatedAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime UpdatedAt;



}
