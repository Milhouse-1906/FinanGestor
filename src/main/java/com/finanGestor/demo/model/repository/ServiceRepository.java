package com.finanGestor.demo.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.finanGestor.demo.model.entity.Service;

public interface ServiceRepository extends JpaRepository <Service, Long> {
}