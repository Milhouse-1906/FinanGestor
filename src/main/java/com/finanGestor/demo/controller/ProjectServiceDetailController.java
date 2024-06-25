package com.finanGestor.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanGestor.demo.model.entity.ProjectServiceDetail;
import com.finanGestor.demo.service.ProjectServiceDetailService;


@RestController
@RequestMapping("/service")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:5500" }, maxAge = 3600)
public class ProjectServiceDetailController {
	@Autowired
	private ProjectServiceDetailService projectServiceDetailService;

	@PostMapping
	public ResponseEntity<ProjectServiceDetail> createServiceDetail(@RequestBody ProjectServiceDetail serviceDetail) {
		ProjectServiceDetail createdServiceDetail = projectServiceDetailService
				.createProjectServiceDetail(serviceDetail);
		return new ResponseEntity<>(createdServiceDetail, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProjectServiceDetail>> getAllServiceDetails() {
		List<ProjectServiceDetail> serviceDetails = projectServiceDetailService.getAllProjectServiceDetails();
		return new ResponseEntity<>(serviceDetails, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectServiceDetail> getServiceDetailById(@PathVariable Long id) {
		ProjectServiceDetail serviceDetail = projectServiceDetailService.getProjectServiceDetailById(id);
		if (serviceDetail != null) {
			return new ResponseEntity<>(serviceDetail, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProjectServiceDetail> updateServiceDetail(@PathVariable Long id,
			@RequestBody ProjectServiceDetail serviceDetailDetails) {
		ProjectServiceDetail updatedServiceDetail = projectServiceDetailService.updateProjectServiceDetail(id,
				serviceDetailDetails);
		if (updatedServiceDetail != null) {
			return new ResponseEntity<>(updatedServiceDetail, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteServiceDetail(@PathVariable Long id) {
		projectServiceDetailService.deleteProjectServiceDetail(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
