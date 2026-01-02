package com.hospital.base.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productName;
	private String productDescr;
	private Double price;
	private boolean enabled;
	public Product() {
		super();
	}
	
	

	public Product(int id, String productName, String productDescr, Double price, boolean enabled) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDescr = productDescr;
		this.price = price;
		this.enabled = enabled;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	public String getProductDescr() {
		return productDescr;
	}



	public void setProductDescr(String productDescr) {
		this.productDescr = productDescr;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productDescr=" + productDescr + ", price="
				+ price + ", enabled=" + enabled + "]";
	}


 
}
