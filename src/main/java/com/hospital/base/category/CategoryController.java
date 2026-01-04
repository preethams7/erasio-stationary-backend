package com.hospital.base.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	  @PostMapping(value = "/create",
			    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<?> createCategory(
	            @RequestParam("category") String category,
	            @RequestParam("descr") String descr,
	            @RequestParam("enabled") boolean enabled,
	            @RequestParam("image") MultipartFile image
	    ) throws Exception {

	        Category cat = new Category();
	        cat.setCategory(category);
	        cat.setDescr(descr);
	        cat.setEnabled(enabled);

	        // 🔥 THIS is where byte[] comes from
	        cat.setImage(image.getBytes());

	        categoryService.save(cat);

	        return ResponseEntity.ok("Category created successfully");
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
