package com.finanGestor.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finanGestor.demo.model.entity.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {
}