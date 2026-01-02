package com.hospital.base.restaurant.foodcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/foodcategory")
public class FoodCategoryController {

	@Autowired
	FoodCategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<FoodCategory> save(@RequestBody FoodCategory category) {
		FoodCategory savedCategory = categoryService.save(category);
		return ResponseEntity.ok(savedCategory);

	}
	@PutMapping("/updatestatus")
	public ResponseEntity<FoodCategory> updateStatus(@RequestBody FoodCategory category) {
		FoodCategory savedCategory = categoryService.updateStatuswithUnicode(category);
		return ResponseEntity.ok(savedCategory);

	}
	
	@GetMapping("/unicode={unicode}")
	public ResponseEntity<List<FoodCategory>> getAllCategoryByUnicode(@PathVariable("unicode") String unicode){
		List<FoodCategory> allCategoryByUnicode = categoryService.getAllCategoryByUnicode(unicode);
		return ResponseEntity.ok(allCategoryByUnicode);
	}
	
}
