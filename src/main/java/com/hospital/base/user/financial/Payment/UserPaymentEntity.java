package com.hospital.base.user.financial.Payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "payment")

public class UserPaymentEntity {

	public UserPaymentEntity() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "currency")
	private String paymentCurrency;
	
	@Column(name = "amount")
	private String paymentAmount;
	
	@Column(name = "payment_status")
	private String paymentStatus;
	
	@Column(name = "payment_ref")
	private String paymentRefCode;
	
	@Transient
	private String razorpay_payment_id;
	@Transient
	private String razorpay_order_id;
	@Transient
	private String razorpay_signature;
	
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



	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentRefCode() {
		return paymentRefCode;
	}

	public void setPaymentRefCode(String paymentRefCode) {
		this.paymentRefCode = paymentRefCode;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getRazorpay_payment_id() {
		return razorpay_payment_id;
	}

	public void setRazorpay_payment_id(String razorpay_payment_id) {
		this.razorpay_payment_id = razorpay_payment_id;
	}

	public String getRazorpay_order_id() {
		return razorpay_order_id;
	}

	public void setRazorpay_order_id(String razorpay_order_id) {
		this.razorpay_order_id = razorpay_order_id;
	}

	public String getRazorpay_signature() {
		return razorpay_signature;
	}

	public void setRazorpay_signature(String razorpay_signature) {
		this.razorpay_signature = razorpay_signature;
	}

}
