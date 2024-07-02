package com.finanGestor.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanGestor.demo.model.entity.Project;
import com.finanGestor.demo.model.entity.ProjectServiceDetail;
import com.finanGestor.demo.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable Long id) {
		return projectService.getProjectById(id);
	}

	@PostMapping
	public Project createProject(@RequestBody Project project) {
		return projectService.createProject(project);
	}

	@PostMapping("/{projectId}/services")
	public Project addServiceToProject(@PathVariable Long projectId, @RequestBody ProjectServiceDetail serviceDetail) {
		return projectService.addServiceToProject(projectId, serviceDetail);
	}

	@DeleteMapping("/{projectId}/services/{serviceId}")
	public Project removeServiceFromProject(@PathVariable Long projectId, @PathVariable Long serviceId) {
		return projectService.removeServiceFromProject(projectId, serviceId);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
		Project updatedProject = projectService.updateProject(id, project);
		if (updatedProject == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedProject);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
		return ResponseEntity.noContent().build();
	}
}
