package com.finanGestor.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanGestor.demo.model.entity.Project;
import com.finanGestor.demo.model.entity.ProjectServiceDetail;
import com.finanGestor.demo.model.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            project.setName(projectDetails.getName());
            project.setBudget(projectDetails.getBudget());
            project.setCategory(projectDetails.getCategory());
            project.setCost(projectDetails.getCost());
            project.setServices(projectDetails.getServices());
            return projectRepository.save(project);
        }
        return null;
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    public Project removeServiceFromProject(Long projectId, Long serviceId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            ProjectServiceDetail serviceToRemove = project.getServices().stream()
                .filter(service -> service.getId().equals(serviceId))
                .findFirst()
                .orElse(null);
            if (serviceToRemove != null) {
                project.removeService(serviceToRemove);
                return projectRepository.save(project);
            }
        }
        return null;
    }

    @Transactional
    public Project addServiceToProject(Long projectId, ProjectServiceDetail newService) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            project.addService(newService);
            return projectRepository.save(project);
        }
        return null;
    }
}
