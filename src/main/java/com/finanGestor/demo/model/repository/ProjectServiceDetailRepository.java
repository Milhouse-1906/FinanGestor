package com.finanGestor.demo.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.finanGestor.demo.model.entity.ProjectServiceDetail;

public interface ProjectServiceDetailRepository extends JpaRepository <ProjectServiceDetail, Long> {
}