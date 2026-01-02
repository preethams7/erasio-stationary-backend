package com.hospital.base.user.financial.Payment.PaymentNetBanking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paymentnetbanking")

public class PaymentNetBankingEntity {

	public PaymentNetBankingEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "account_number")
	private Long accountnumber;
	
	@Column(name = "routing_ifsc_swift")
	private Long routingIFSCSwift;
	
		
	public String toString() {
		return "PaymentNetBankingEntity [id=" + id + ", accountnumber=" + accountnumber + ", routingifscswift=" + routingIFSCSwift +"]";
	}

	public PaymentNetBankingEntity(Long accountnumber, Long routingIFSCSwift) {
		this.accountnumber = accountnumber;
		this.routingIFSCSwift = routingIFSCSwift;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(Long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public Long getRoutingIFSCSwift() {
		return routingIFSCSwift;
	}

	public void setRoutingIFSCSwift(Long routingIFSCSwift) {
		this.routingIFSCSwift = routingIFSCSwift;
	}

	

			
}
