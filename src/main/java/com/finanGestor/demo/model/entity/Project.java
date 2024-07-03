package com.finanGestor.demo.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double cost;
	private double budget;
	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private ProjectCategory category;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProjectServiceDetail> services = new ArrayList<>();

	public Project() {
	}

	public Project(Long id, String name, double budget, double cost, ProjectCategory category, String description,
			List<ProjectServiceDetail> services) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.budget = budget;
		this.category = category;
		this.description = description;
		this.services = services;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public ProjectCategory getCategory() {
		return category;
	}

	public void setCategory(ProjectCategory category) {
		this.category = category;
	}

	public List<ProjectServiceDetail> getServices() {
		return services;
	}

	public void setServices(List<ProjectServiceDetail> services) {
		this.services = services;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
