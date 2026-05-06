package org.example.devboardproduct.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.scheduling.config.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, name = "name", length = 100)
    private String name;


    @Column(unique = true, nullable = false)
    @NotNull(message="{email.required}")
    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
            + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?",
            message = "{invalid.email}")
    private String email;

    @Enumerated(EnumType.STRING)
    private userRole Role = userRole.Developer; // by default to Developer

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST})
    private List<Project> ownedProjects = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    private List<Project> memberProjects = new ArrayList<>();

    @OneToMany(mappedBy = "assignee")
    private List<Tasks> assignedTasks = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
