package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.Project;

import java.util.UUID;

public interface projectService {
    Project createProject(String name, int ownerId);
    Project addMemberToProject(UUID projectId, int memberId);
    Project removeMemberToProject(UUID projectId, int memberId);
}
