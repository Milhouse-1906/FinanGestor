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
			return projectRepository.save(project);
		}
		return null;
	}

	public void deleteProject(Long id) {
		projectRepository.deleteById(id);
	}
}
