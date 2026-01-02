package com.hospital.base.restaurant.foodcategory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {

	FoodCategory findByIdAndRestaurantUnicode(int id, String restaurantUnicode);

	List<FoodCategory> findByRestaurantUnicode(String unicode);

}
