package com.finanGestor.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finanGestor.demo.model.entity.ProjectCategory;

public interface CategoryRepository extends JpaRepository<ProjectCategory, Long> {

}