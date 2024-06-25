package com.finanGestor.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanGestor.demo.model.entity.ProjectServiceDetail;
import com.finanGestor.demo.model.repository.ProjectServiceDetailRepository;

@Service
public class ProjectServiceDetailService {

	@Autowired
	private ProjectServiceDetailRepository projectServiceDetailRepository;

	public ProjectServiceDetail createProjectServiceDetail(ProjectServiceDetail projectServiceDetail) {
		return projectServiceDetailRepository.save(projectServiceDetail);
	}

	public List<ProjectServiceDetail> getAllProjectServiceDetails() {
		return projectServiceDetailRepository.findAll();
	}

	public ProjectServiceDetail getProjectServiceDetailById(Long id) {
		return projectServiceDetailRepository.findById(id).orElse(null);
	}

	public ProjectServiceDetail updateProjectServiceDetail(Long id, ProjectServiceDetail projectServiceDetail) {
		ProjectServiceDetail existingProjectServiceDetail = projectServiceDetailRepository.findById(id).orElse(null);
		if (existingProjectServiceDetail != null) {
			existingProjectServiceDetail.setName(projectServiceDetail.getName());
			existingProjectServiceDetail.setDescription(projectServiceDetail.getDescription());
			existingProjectServiceDetail.setProject(projectServiceDetail.getProject());
			return projectServiceDetailRepository.save(existingProjectServiceDetail);
		}
		return null;
	}

	public void deleteProjectServiceDetail(Long id) {
		projectServiceDetailRepository.deleteById(id);
	}

}