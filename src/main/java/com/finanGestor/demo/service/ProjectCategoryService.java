package com.finanGestor.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanGestor.demo.model.entity.ProjectCategory;
import com.finanGestor.demo.model.repository.CategoryRepository;

@Service
public class ProjectCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ProjectCategory createCategory(ProjectCategory category) {
        return categoryRepository.save(category);
    }

    public List<ProjectCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ProjectCategory getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public ProjectCategory updateCategory(Long id, ProjectCategory categoryDetails) {
        ProjectCategory category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(categoryDetails.getName());
            return categoryRepository.save(category);
        }
        return null;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}