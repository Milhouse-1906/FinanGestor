package com.finanGestor.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanGestor.demo.model.entity.Project;
import com.finanGestor.demo.model.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        Project existingProject = projectRepository.findById(id).orElse(null);
        if (existingProject != null) {
            existingProject.setName(project.getName());
            existingProject.setBudget(project.getBudget());
            existingProject.setCost(project.getCost());
            existingProject.setCategory(project.getCategory());
            existingProject.setServices(project.getServices());
            return projectRepository.save(existingProject);
        } else {
            return null;
        }
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
    
}