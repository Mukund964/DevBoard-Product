package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.Project;
import org.example.devboardproduct.entities.User;
import org.example.devboardproduct.repository.ProjectRepository;
import org.example.devboardproduct.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        if(userRepo.existsById(ownerId)) throw new RuntimeException("User Not Found");
        User userFound = userRepo.findById(ownerId);

        Project newProject = Project.builder().Name(name).owner(userFound).
                            build();
    }
}
