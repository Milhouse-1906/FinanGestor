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

import com.finanGestor.demo.model.entity.ProjectCategory;
import com.finanGestor.demo.service.ProjectCategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:5500" }, maxAge = 3600)
public class ProjectCategoryController {
	
	@Autowired
    private ProjectCategoryService projectCategoryService;

    @PostMapping
    public ResponseEntity<ProjectCategory> createCategory(@RequestBody ProjectCategory category) {
        ProjectCategory createdCategory = projectCategoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectCategory>> getAllCategories() {
        List<ProjectCategory> categories = projectCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectCategory> getCategoryById(@PathVariable Long id) {
        ProjectCategory category = projectCategoryService.getCategoryById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectCategory> updateCategory(@PathVariable Long id, @RequestBody ProjectCategory categoryDetails) {
        ProjectCategory updatedCategory = projectCategoryService.updateCategory(id, categoryDetails);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        projectCategoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
