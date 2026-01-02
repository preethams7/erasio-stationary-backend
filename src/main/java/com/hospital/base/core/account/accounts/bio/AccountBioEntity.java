package com.hospital.base.core.account.accounts.bio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "account_bio", uniqueConstraints = @UniqueConstraint(columnNames = { "tx_email" }))

public class AccountBioEntity {

	public AccountBioEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "company")
	private String company;

	@Column(name = "tx_email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "web")
	private String web;

	@Column(name = "bio", columnDefinition="TEXT")
	private String bio;

	@Column(name = "image_meta")
	private String imageMeta;

	@Column(name = "image", unique = false,  length = 100000000)
	private byte[] image;

	@Column(name="taxid")
	private String taxid;
	
	@Column(name="country")
	private String country;

	
	public String toString() {
		return "AccountBioEntity [id=" + id + ", firstname=" + firstname   + ", lastname=" + lastname+ ", email=" + email+ ", phone=" + phone
				 + ", web=" + web
				+ ", bio=" + bio + ", image=" + image+ "]";
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getWeb() {
		return web;
	}



	public void setWeb(String web) {
		this.web = web;
	}



	public String getBio() {
		return bio;
	}



	public void setBio(String bio) {
		this.bio = bio;
	}



	public byte[] getImage() {
		return image;
	}



	public void setImage(byte[] image) {
		this.image = image;
	}



	public String getImageMeta() {
		return imageMeta;
	}



	public void setImageMeta(String imageMeta) {
		this.imageMeta = imageMeta;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}






	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getTaxid() {
		return taxid;
	}



	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}






	 
}
