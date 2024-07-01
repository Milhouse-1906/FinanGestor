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

	@PutMapping("/service/{projectId}")
	public ResponseEntity<Project> addServiceToProject(@PathVariable Long projectId,
			@RequestBody ProjectServiceDetail serviceDetail) {
		Project savedProject = projectService.addServiceToProject(projectId, serviceDetail);
		if (savedProject == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(savedProject);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
		Project updatedProject = projectService.updateProject(id, project);
		if (updatedProject == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedProject);
	}

	@DeleteMapping("/{projectId}/services/{serviceId}")
	public ResponseEntity<Project> removeServiceFromProject(@PathVariable Long projectId,
	        @PathVariable Long serviceId) {
	    System.out.println("Removendo serviço do projeto: projectId=" + projectId + ", serviceId=" + serviceId);
	    Project updatedProject = projectService.removeServiceFromProject(projectId, serviceId);
	    if (updatedProject != null) {
	        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
		return ResponseEntity.noContent().build();
	}
}
