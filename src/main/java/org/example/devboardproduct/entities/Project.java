package org.example.devboardproduct.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID Id;

    @Column(nullable = false, unique = true)
    private String Name;

    private String Description;

    @OneToOne
    private User owner;

    @ManyToMany
    private User members;

    @ManyToOne
    private Tasks tasks;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
