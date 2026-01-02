package com.hospital.base.restaurant.food;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.hospital.base.restaurant.foodcategory.FoodCategory;

@Entity
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Food name is required")
	private String item;
	@NotBlank(message = "Description is required")
	private String itemDescription;

	private byte[] image;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private FoodCategory category;

	private String unicode;

	private double price;

	private boolean disable;

	public Food() {
		super();
	}

	public Food(int id, @NotBlank(message = "Food name is required") String item,
			@NotBlank(message = "Description is required") String itemDescription, byte[] image, FoodCategory category,
			String unicode, double price, boolean disable) {
		super();
		this.id = id;
		this.item = item;
		this.itemDescription = itemDescription;
		this.image = image;
		this.category = category;
		this.unicode = unicode;
		this.price = price;
		this.disable = disable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public FoodCategory getCategory() {
		return category;
	}

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	public void setCategory(FoodCategory category) {
		this.category = category;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", item=" + item + ", itemDescription=" + itemDescription + ", image="
				+ Arrays.toString(image) + ", category=" + category + ", unicode=" + unicode + ", price=" + price
				+ ", disable=" + disable + "]";
	}

}
