package com.hospital.base.user.subscriptions;

import com.hospital.base.user.financial.Payment.PaymentDO;

public class AccountSubscriptionDO {

	private PaymentDO payment;
	
	private int duration;

	public PaymentDO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDO payment) {
		this.payment = payment;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	} 
}
