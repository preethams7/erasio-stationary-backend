package com.hospital.base.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String category;

	private boolean enabled;

	public Category() {
		super();
	}

	

	public Category(int id, String category,  boolean enabled) {
		super();
		this.id = id;
		this.category = category;
	
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



	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + ", enabled=" + enabled + "]";
	}




	


	
}
