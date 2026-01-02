package com.hospital.base.user.financial.Payment;

import org.springframework.stereotype.Component;


@Component
public class PaymentActions {


	public UserPaymentEntity processPaymentDO(String username, PaymentDO payment) {
		UserPaymentEntity paymentEntity = new UserPaymentEntity();

		paymentEntity.setUsername(username);
		paymentEntity.setPaymentCurrency(payment.getCurrency());
		paymentEntity.setPaymentAmount(payment.getAmount());
		paymentEntity.setPaymentRefCode(payment.getReceipt());
		
		paymentEntity.setRazorpay_order_id(payment.getRazorpay_order_id());
		paymentEntity.setRazorpay_payment_id(payment.getRazorpay_payment_id());
		paymentEntity.setRazorpay_signature(payment.getRazorpay_signature());
		return paymentEntity;
		
	}

}
