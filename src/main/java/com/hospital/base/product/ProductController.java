package com.hospital.base.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/create")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product cratedProduct = productService.create(product);
		return ResponseEntity.ok(cratedProduct);
	}

	public ResponseEntity<Product> getById(@PathVariable int id) {
		Product product = productService.getById(id);
		return ResponseEntity.ok(product);
	}

	public ResponseEntity<List<Product>> getAll() {
		List<Product> products = productService.getAll();
		return ResponseEntity.ok(products);
	}
}
