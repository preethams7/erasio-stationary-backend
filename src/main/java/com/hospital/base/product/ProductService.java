package com.hospital.base.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public Product create(Product product) {
		return productRepository.save(product);
	}

	public Product getById(int id) {
		
		return productRepository.findById(id).get();
	}
	
	public List<Product> getAll(){
		return productRepository.findAll();
	}
	
}
