package com.hospital.base.user.subscriptions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_subscriptions")
public class AccountSubscriptionEntity {

	public AccountSubscriptionEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tx_email")
	private String username;

	@Column(name="subscription_code")
	private String subcode;
	

	@Column(name="subscription_date")
	private String subdate;
	
	@Column(name="expiry_date")
	private String expires;

	@Column(name="transactionLink")
	private String transactionLink;
	
	public String toString() {
		return "AccountSubscriptionEntity [id=" + id + ", email=" + username  + ", Subscription Date=" + subdate +
				", SubScription Code =" + subcode+
				", SubScription Expired on =" + expires +"]";
	}

	public String getSubdate() {
		return subdate;
	}

	public void setSubdate(String subdate) {
		this.subdate = subdate;
	}

	public AccountSubscriptionEntity(String username, boolean required, String subdate, String subcode, String expires) {
		this.username = username;
		this.subcode = subcode;
		this.subdate = subdate;
		this.expires = expires;
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

	
	public String getSubcode() {
		return subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getTransactionLink() {
		return transactionLink;
	}

	public void setTransactionLink(String transactionLink) {
		this.transactionLink = transactionLink;
	}

}
