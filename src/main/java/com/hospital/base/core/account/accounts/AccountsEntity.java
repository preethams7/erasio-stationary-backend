package com.hospital.base.core.account.accounts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.hospital.base.core.account.authority.AccountAuthorityEntity;
import com.hospital.base.restaurant.restaurant.Restaurant;

@Entity
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = { "tx_email" }))

public class AccountsEntity {

	public AccountsEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tx_email")
	private String username;

	@Column(name = "tx_password")
	private String password;

	@Transient
	private String passwordConfirm;
	
	

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@Column(name = "fl_disabled")
	private boolean disabled;

	@Column(name = "ts_create")
	private String createdTime;

	@Column(name = "tx_temp_auth")
	private String tempAuthCode;
	
	@Column(name = "role")
	private String role;
	
	@Column(name="email_validate")
	private boolean validated;
	 
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	@Override
	public String toString() {
		return "AccountsEntity [id=" + id + ", username=" + username + ", password=" + password + ", passwordConfirm="
				+ passwordConfirm + ", disabled=" + disabled + ", createdTime=" + createdTime + ", tempAuthCode="
				+ tempAuthCode + ", role=" + role + ", validated=" + validated + ", roles=" + roles + "]";
	}

	@Transient
	private List<AccountAuthorityEntity> roles;

	

	public AccountsEntity(Long id, String username, String password, String passwordConfirm,
			boolean disabled, String createdTime, String tempAuthCode, List<AccountAuthorityEntity> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		
		this.disabled = disabled;
		this.createdTime = createdTime;
		this.tempAuthCode = tempAuthCode;
		this.roles = roles;
	}

	public List<AccountAuthorityEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<AccountAuthorityEntity> list) {
		this.roles = list;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getTempAuthCode() {
		return tempAuthCode;
	}

	public void setTempAuthCode(String tempAuthCode) {
		this.tempAuthCode = tempAuthCode;
	}

	
}
