package com.finanGestor.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanGestor.demo.model.entity.Project;
import com.finanGestor.demo.model.entity.ProjectServiceDetail;
import com.finanGestor.demo.model.repository.ProjectRepository;
import com.finanGestor.demo.model.repository.ProjectServiceDetailRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProjectServiceDetailRepository projectServiceDetailRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    private double calcularTudo(Project project) {
    	double custoTotal = 0;
    	for (ProjectServiceDetail service : project.getServices()) {
            custoTotal += service.getCost();
        };
		return custoTotal;
	}
    
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
    	Project projeto = projectRepository.findById(id).orElse(null);
    	projeto.setCost(calcularTudo(projeto));
        return projeto;
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            project.setName(projectDetails.getName());
            project.setBudget(projectDetails.getBudget());
            project.setCategory(projectDetails.getCategory());
            project.setServices(projectDetails.getServices());
            return projectRepository.save(project);
        }
        return null;
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    public Project addServiceToProject(Long projectId, ProjectServiceDetail serviceDetail) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.getServices().add(serviceDetail);
            serviceDetail.setProject(project);
            projectServiceDetailRepository.save(serviceDetail);
            projectRepository.save(project);
            return project;
        }
        throw new RuntimeException("Project not found with id " + projectId);
    }

    @Transactional
    public Project removeServiceFromProject(Long projectId, Long serviceId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            ProjectServiceDetail serviceDetail = project.getServices().stream()
                    .filter(service -> service.getId().equals(serviceId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Service not found with id " + serviceId));
            project.getServices().remove(serviceDetail);
            serviceDetail.setProject(null);
            projectServiceDetailRepository.delete(serviceDetail);
            projectRepository.save(project);
            return project;
        }
        throw new RuntimeException("Project not found with id " + projectId);
    }
}
