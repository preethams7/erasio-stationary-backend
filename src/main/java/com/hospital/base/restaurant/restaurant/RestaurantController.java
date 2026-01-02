package com.hospital.base.restaurant.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.Response;

@RestController
@RequestMapping("/restuarant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
   
	@PostMapping("/save")
	public ResponseEntity<Restaurant> save(@RequestBody Restaurant restuarant) {
		Restaurant savedRestaurant = restaurantService.save(restuarant);
		return ResponseEntity.ok(savedRestaurant);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant){
		Restaurant updatedRestaurant = restaurantService.update(restaurant);
		return ResponseEntity.ok(updatedRestaurant);
	}
	@PutMapping("/updatestatus")
	public ResponseEntity<Restaurant> updateStatus(@RequestBody Restaurant restaurant){
		Restaurant updatedRestaurant = restaurantService.updateStatus(restaurant);
		return ResponseEntity.ok(updatedRestaurant);
	}
	@GetMapping("/id={id}")
	public ResponseEntity<Restaurant> getById(@PathVariable("id") int id){
		Restaurant restaurant = restaurantService.getById(id);
		return ResponseEntity.ok(restaurant);
	}
	@GetMapping("/")
	public ResponseEntity<List<Restaurant>> getByAll(){
		List<Restaurant>  restaurant = restaurantService.getAll();
		return ResponseEntity.ok(restaurant);
	}
	
}
