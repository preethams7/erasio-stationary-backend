package com.hospital.base.restaurant.restaurant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.hospital.base.core.account.accounts.AccountsEntity;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String restaurantName;
	private String restaurantDescr;
	private String restuarantUnicode;
	private String city;
	private String address;
	private boolean enabled;
	
	
	public Restaurant(int id, String restaurantName, String restaurantDescr, String restuarantUnicode, String city,
			String address, AccountsEntity account, boolean enabled) {
		super();
		this.id = id;
		this.restaurantName = restaurantName;
		this.restaurantDescr = restaurantDescr;
		this.restuarantUnicode = restuarantUnicode;
		this.city = city;
		this.address = address;
		this.enabled = enabled;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Restaurant() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantDescr() {
		return restaurantDescr;
	}
	public void setRestaurantDescr(String restaurantDescr) {
		this.restaurantDescr = restaurantDescr;
	}
	public String getRestuarantUnicode() {
		return restuarantUnicode;
	}
	public void setRestuarantUnicode(String restuarantUnicode) {
		this.restuarantUnicode = restuarantUnicode;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", restaurantName=" + restaurantName + ", restaurantDescr=" + restaurantDescr
				+ ", restuarantUnicode=" + restuarantUnicode + ", city=" + city + ", address=" + address + ", enabled="
				+ enabled + "]";
	}
	
	
	
	
	
	
}
