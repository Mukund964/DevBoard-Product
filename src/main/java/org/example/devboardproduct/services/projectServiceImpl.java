package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.Project;
import org.example.devboardproduct.entities.User;
import org.example.devboardproduct.repository.ProjectRepository;
import org.example.devboardproduct.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class projectServiceImpl implements projectService{


    public final ProjectRepository projectRepo;
    public final UserRepository userRepo;

    public projectServiceImpl(ProjectRepository projectRepo, UserRepository userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    @Override
        public Project createProject(String name, int ownerId) {

            User owner = userRepo.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("User with ID " + ownerId + " not found"));


            Project newProject = Project.builder()
                    .Name(name)
                    .Description("This is a dummy Description by default")
                    .owner(owner)
                    .build();


            return projectRepo.save(newProject);
        }
    }

