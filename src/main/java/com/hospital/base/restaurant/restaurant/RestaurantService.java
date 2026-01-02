package com.hospital.base.restaurant.restaurant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping("/save")
    public Restaurant save(@RequestBody Restaurant restaurant) {
        String unicode = generateRestaurantCode(restaurant);
        restaurant.setRestuarantUnicode(unicode); 
        return restaurantRepository.save(restaurant);
    }

    private String generateRestaurantCode(Restaurant restaurant) {
        String namePart = getShortCode(restaurant.getRestaurantName());
        String cityPart = getShortCode(restaurant.getCity()); 
        long count = restaurantRepository.count() + 1; 

        return namePart + "-" + cityPart + "-" + String.format("%04d", count); 
    }

    private String getShortCode(String input) {
        if (input == null || input.length() < 3) {
            return "XXX"; 
        }
        return input.substring(0, 3).toUpperCase();
    }

	public Restaurant update(Restaurant restaurant) {
		
		int id = restaurant.getId();
		Restaurant existingRestaurant  = restaurantRepository.findById(id).get();
		if (existingRestaurant!=null) {
			existingRestaurant.setRestaurantName(restaurant.getRestaurantName()!=null?restaurant.getRestaurantName():existingRestaurant.getRestaurantName());
			existingRestaurant.setAddress(restaurant.getAddress()!=null?restaurant.getAddress():existingRestaurant.getAddress());
			existingRestaurant.setRestaurantDescr(restaurant.getRestaurantDescr()!=null?restaurant.getRestaurantDescr():existingRestaurant.getRestaurantDescr());
			existingRestaurant = restaurantRepository.save(existingRestaurant);
			return existingRestaurant;
			
		}
		return existingRestaurant;
	}

	public Restaurant updateStatus(Restaurant restaurant) {
	
		int id = restaurant.getId();
		Restaurant existingRestaurant  = restaurantRepository.findById(id).get();
		if (existingRestaurant!=null) {
			existingRestaurant.setEnabled(restaurant.isEnabled());
			existingRestaurant = restaurantRepository.save(existingRestaurant);
			return existingRestaurant;
		}
		return existingRestaurant;
	}

	public Restaurant getById(int id) {
		Restaurant restaurant = restaurantRepository.findById(id).get();
		return restaurant;
	}

	public List<Restaurant> getAll() {
		List<Restaurant> restaurant = restaurantRepository.findAll();
		return restaurant;
	}
}
