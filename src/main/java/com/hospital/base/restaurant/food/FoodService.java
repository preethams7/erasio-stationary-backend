package com.hospital.base.restaurant.food;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.restaurant.foodcategory.FoodCategory;
import com.hospital.base.restaurant.foodcategory.FoodCategoryRepository;
import com.hospital.base.restaurant.foodcategory.FoodCategoryService;

@Service
public class FoodService {

	@Autowired
	FoodRepository foodRepository;

	@Autowired
	FoodCategoryService categoryService;

	@Autowired
	FoodCategoryRepository categoryRepository;

	public Food saveFood(Food food) {
		FoodCategory foodCategory = categoryRepository.findById(food.getCategory().getId()).get();
		food.setCategory(foodCategory);
		food.setUnicode(foodCategory.getRestaurantUnicode()); 
		return foodRepository.save(food);
	}

	public Food updateFood(Food food) {
		Food existingFood = foodRepository.findById(food.getId()).get();
		if (existingFood != null) {
			existingFood.setItem(food.getItem() != null ? food.getItem() : existingFood.getItem());
			existingFood.setItemDescription(
					food.getItemDescription() != null ? food.getItemDescription() : existingFood.getItemDescription());
			existingFood.setCategory(food.getCategory() != null ? food.getCategory() : existingFood.getCategory());
			existingFood.setUnicode(food.getUnicode() != null ? food.getUnicode() : existingFood.getUnicode());
			return foodRepository.save(existingFood);
		} else {
			return existingFood;
		}
	}

	public Food updateStatus(Food food) {
		Food existingFood = foodRepository.findById(food.getId()).get();
		if (existingFood != null) {
			existingFood.setDisable(food.isDisable());
			return foodRepository.save(existingFood);
		}
		return existingFood;
	}
}
