package com.hospital.base.user.financial.transactions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")

public class TransactionEntity {

	public TransactionEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;
	
	@Column(name = "transaction_type")
	private String transactionType;
	
	@Column(name = "payment_link")
	private String paymentLink;
	
	@Column(name = "transaction_status")
	private boolean transactionStatus;
	
	public String toString() {
		return "TransactionEntity [id=" + id + ", username=" + username + ", transactionType=" + transactionType +", transactionStatus=" + transactionStatus +  ",paymentLink=" + paymentLink +"]";
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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getPaymentLink() {
		return paymentLink;
	}

	public void setPaymentLink(String paymentLink) {
		this.paymentLink = paymentLink;
	}

	public boolean isTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(boolean transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


		
		}
