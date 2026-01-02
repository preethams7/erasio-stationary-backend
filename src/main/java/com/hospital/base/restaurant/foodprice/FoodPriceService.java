package com.hospital.base.restaurant.foodprice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodPriceService {

	@Autowired
	FoodPriceRepository foodPriceRepository;
	
	public FoodPrice save(FoodPrice foodPrice) {
		FoodPrice savedFoodPrice = foodPriceRepository.save(foodPrice);
		return savedFoodPrice;
	}
}
