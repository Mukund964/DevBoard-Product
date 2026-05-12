package org.example.devboardproduct.services;

import jakarta.transaction.Transactional;
import org.example.devboardproduct.entities.Project;
import org.example.devboardproduct.entities.User;
import org.example.devboardproduct.repository.ProjectRepository;
import org.example.devboardproduct.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


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
                   // .Description("This is a dummy Description by default")
                    .owner(owner)
                    .build();


            return projectRepo.save(newProject);
        }

    @Override
    public Project addMemberToProject(UUID projectId, int memberId) {
        Project projectWithGivenId = projectRepo.findById(projectId)
                .orElseThrow(()->  new RuntimeException("Project not found"));

        User memberToAdd = userRepo.findById(memberId)
                .orElseThrow(()-> new RuntimeException("Member doesn't exists"));

        if(projectWithGivenId.getMembers().contains(memberToAdd)) return projectWithGivenId;

        projectWithGivenId.getMembers().add(memberToAdd);
        memberToAdd.getMemberProjects().add(projectWithGivenId);

        return projectRepo.save(projectWithGivenId);
    }

    @Override
    @Transactional
    public Project removeMemberToProject(UUID projectId, int memberId) {
        Project projectWithGivenId = projectRepo.findById(projectId)
                .orElseThrow(()->  new RuntimeException("Project not found"));

        User memberToRemove = userRepo.findById(memberId)
                .orElseThrow(()-> new RuntimeException("Member doesn't exists"));

        if(!projectWithGivenId.getMembers().contains(memberToRemove)) return projectWithGivenId;

        projectWithGivenId.getMembers().remove(memberToRemove);
        memberToRemove.getMemberProjects().remove(projectWithGivenId);

        return projectRepo.save(projectWithGivenId);
    }
}

