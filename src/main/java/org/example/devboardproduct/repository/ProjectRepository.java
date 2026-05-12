package org.example.devboardproduct.repository;

import org.example.devboardproduct.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    Optional<Project> findById(UUID projectId);
}
