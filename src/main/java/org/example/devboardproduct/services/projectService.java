package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.Project;

public interface projectService {
    Project createProject(String name, int ownerId);
}
