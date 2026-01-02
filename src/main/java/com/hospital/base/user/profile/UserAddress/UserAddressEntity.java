package com.hospital.base.user.profile.UserAddress;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useraddress")

public class UserAddressEntity {

	public UserAddressEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;
	
	@Column(name = "address_name")
	private String addressName;
	
	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;
	
	@Column(name = "address3")
	private String address3;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;

	@Column(name = "fl_default")
	private boolean defaults;

		
	public String toString() {
		return "UserAddressEntity [id=" + id + ", username=" + username + ", address1=" + address1 + ", address2=" + address2 +  ",address3 ="+ address3 + ",city=" + city + ", pincode=" + pincode + ", state=" + state +",country=" + country +""
				+ ",defaults=" + defaults +"]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getAddress3() {
		return address3;
	}


	public void setAddress3(String address3) {
		this.address3 = address3;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public boolean isDefaults() {
		return defaults;
	}


	public void setDefaults(boolean defaults) {
		this.defaults = defaults;
	}


	public String getAddressName() {
		return addressName;
	}


	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}


	


	
	
		}
