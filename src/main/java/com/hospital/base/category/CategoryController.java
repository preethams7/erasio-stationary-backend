package com.hospital.base.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<Category> save(@RequestBody Category category) {
		Category savedCategory = categoryService.save(category);
		return ResponseEntity.ok(savedCategory);

	}

	@PutMapping("/updatestatus")
	public ResponseEntity<Category> updateStatus(@RequestBody Category category) {
		Category savedCategory = categoryService.updateStatus(category);
		return ResponseEntity.ok(savedCategory);

	}

	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> allCategory = categoryService.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}

}
