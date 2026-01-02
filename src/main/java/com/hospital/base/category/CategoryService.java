package com.hospital.base.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository foodCategoryRepository;

	public Category save(Category category) {
		category.setEnabled(true);
		return foodCategoryRepository.save(category);
	}

	public Category getById(int id) {
		Category category = foodCategoryRepository.findById(id).get();
		return category;
	}

	public Category updateStatus(Category category) {
		Category oldCategory = getById(category.getId());
		if (category != null) {
			oldCategory.setCategory(category.getCategory() != null ? category.getCategory() : category.getCategory());
		}
		return category;
	}

	public List<Category> getAllCategory() {
		List<Category> categories = foodCategoryRepository.findAll();
		return categories;
	}
}
