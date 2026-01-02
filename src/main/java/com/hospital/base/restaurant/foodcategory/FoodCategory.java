package com.hospital.base.restaurant.foodcategory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FoodCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String category;
	private String restaurantUnicode;
	private boolean enabled;

	public FoodCategory() {
		super();
	}

	

	public FoodCategory(int id, String category, String restaurantUnicode, boolean enabled) {
		super();
		this.id = id;
		this.category = category;
		this.restaurantUnicode = restaurantUnicode;
		this.enabled = enabled;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRestaurantUnicode() {
		return restaurantUnicode;
	}



	public void setRestaurantUnicode(String restaurantUnicode) {
		this.restaurantUnicode = restaurantUnicode;
	}



	@Override
	public String toString() {
		return "FoodCategory [id=" + id + ", category=" + category + ", restaurantUnicode=" + restaurantUnicode
				+ ", enabled=" + enabled + "]";
	}



	
}
