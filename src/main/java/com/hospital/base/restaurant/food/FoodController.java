package com.hospital.base.restaurant.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {
	@Autowired
	FoodService foodService;

	@PostMapping("/create")
	public ResponseEntity<Food> save(@RequestBody Food food) {
		Food savedFood = foodService.saveFood(food);
		return ResponseEntity.ok(savedFood);
	}
	@PutMapping("/update")
	public ResponseEntity<Food> update(@RequestBody Food food){
		Food updatedFood = foodService.updateFood(food);
		return ResponseEntity.ok(updatedFood);
	}
	@PutMapping("/updatestatus")
	public ResponseEntity<Food> updateStatus(@RequestBody Food food){
		Food updatedFood = foodService.updateFood(food);
		return ResponseEntity.ok(updatedFood);
	}
	
}
