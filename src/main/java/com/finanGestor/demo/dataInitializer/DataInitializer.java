package com.finanGestor.demo.dataInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.finanGestor.demo.model.entity.ProjectCategory;
import com.finanGestor.demo.model.repository.CategoryRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private final CategoryRepository categoryRepository;

	@Autowired
	public DataInitializer(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (categoryRepository.count() == 0) {
			ProjectCategory infra = new ProjectCategory();
			infra.setName("Infra");

			ProjectCategory desenvolvimento = new ProjectCategory();
			desenvolvimento.setName("Desenvolvimento");

			ProjectCategory design = new ProjectCategory();
			design.setName("Design");

			ProjectCategory planejamento = new ProjectCategory();
			planejamento.setName("Planejamento");

			categoryRepository.save(infra);
			categoryRepository.save(desenvolvimento);
			categoryRepository.save(design);
			categoryRepository.save(planejamento);
		}
	}
}
