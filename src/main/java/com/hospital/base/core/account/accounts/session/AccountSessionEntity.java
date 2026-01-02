package com.hospital.base.core.account.accounts.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_sessions")
public class AccountSessionEntity {

	public AccountSessionEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tx_email")
	private String username;

	@Column(name="datetime")
	private String datetime;

	
	public String toString() {
		return "AccountSessionEntity [id=" + id + ", email=" + username + ", datetime=" + datetime + "]";
	}

	public AccountSessionEntity(String username, String datetime) {
		this.username = username;
		this.datetime = datetime;
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

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
}
