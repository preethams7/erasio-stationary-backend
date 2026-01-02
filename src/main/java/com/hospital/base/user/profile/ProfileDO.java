package com.hospital.base.user.profile;

import java.util.List;

import com.hospital.base.user.profile.UserAddress.UserAddressEntity;
import com.hospital.base.user.subscriptions.AccountSubscriptionEntity;

public class ProfileDO {

	private Long userId;
	private String name;
	private String email;
	private String phone;
	private String imageMeta;
	private byte[] image;
	private String company;
	private String lastLogin;
	private String bio;
	private List<UserAddressEntity> defaultAddress;
	private List<AccountSubscriptionEntity> subscriptions;
	private Long notifications;
	private String country;
	private String taxID;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageMeta() {
		return imageMeta;
	}

	public void setImageMeta(String imageMeta) {
		this.imageMeta = imageMeta;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<UserAddressEntity> getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(List<UserAddressEntity> list) {
		this.defaultAddress = list;
	}

	public List<AccountSubscriptionEntity> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<AccountSubscriptionEntity> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNotifications() {
		return notifications;
	}

	public void setNotifications(Long notifications) {
		this.notifications = notifications;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
