package com.hospital.base.restaurant.foodprice;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hospital.base.restaurant.food.Food;
import com.hospital.base.restaurant.quantity.QuantityType;

@Entity
public class FoodPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private QuantityType quantityType;
	private double price;
	@ManyToOne
	@JoinColumn(name = "food_id", nullable = false)
	private Food food;

	public FoodPrice() {
		super();
	}

	public FoodPrice(int id, QuantityType quantityType, double price, Food food) {
		super();
		this.id = id;
		this.quantityType = quantityType;
		this.price = price;
		this.food = food;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public QuantityType getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(QuantityType quantityType) {
		this.quantityType = quantityType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "FoodPrice [id=" + id + ", quantityType=" + quantityType + ", price=" + price + ", food=" + food + "]";
	}

}
