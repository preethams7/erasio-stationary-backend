package com.hospital.base.restaurant.foodcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCategoryService {
	@Autowired
	private FoodCategoryRepository foodCategoryRepository;

	public FoodCategory save(FoodCategory foodCategory) {
		foodCategory.setEnabled(true);
		return foodCategoryRepository.save(foodCategory);
	}

	public FoodCategory getByIdAndUnicode(FoodCategory foodCategory) {
		FoodCategory category = foodCategoryRepository.findByIdAndRestaurantUnicode(foodCategory.getId(),
				foodCategory.getRestaurantUnicode());
		return category;
	}

	public FoodCategory updateStatuswithUnicode(FoodCategory category) {
		FoodCategory foodCategory = getByIdAndUnicode(category);
		if (foodCategory != null) {
			foodCategory
					.setCategory(category.getCategory() != null ? category.getCategory() : foodCategory.getCategory());
			foodCategory.setEnabled(category.isEnabled());

		}
		return foodCategory;
	}

	public List<FoodCategory> getAllCategoryByUnicode(String unicode) {
		List<FoodCategory> restaurants = foodCategoryRepository.findByRestaurantUnicode(unicode);
		return restaurants;
	}
}
