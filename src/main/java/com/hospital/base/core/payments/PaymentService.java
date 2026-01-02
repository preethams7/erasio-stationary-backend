package com.hospital.base.core.payments;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hospital.base.user.financial.Payment.PaymentDO;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@Component
public class PaymentService {
//	@Value("${RAZORPAY_KEY_ID}")
	private String razorPayKey;
	
//	@Value("${RAZORPAY_KEY_SECRET}")
	private String razorPaySecret;
	
	   public boolean paymentprocess(@Valid PaymentDO paymentProcessDO) {
		   RazorpayClient client = null;
	    	boolean result=false;
	    	try {
	    		client = new RazorpayClient(razorPayKey,razorPaySecret);
	    		
	    		JSONObject options = new JSONObject();
	    		options.put("razorpay_payment_id", paymentProcessDO.getRazorpay_payment_id());
	    		options.put("razorpay_order_id", paymentProcessDO.getRazorpay_order_id());
	    		options.put("razorpay_signature", paymentProcessDO.getRazorpay_signature());
	    		boolean SigRes = Utils.verifyPaymentSignature(options, razorPaySecret);
	    		
	    		if(SigRes) {
	    			
	    			result = true;
	    		}else {
	    			
	    			result = false;
	    		}
	    		
	    	}catch(RazorpayException e) {
	    		e.printStackTrace();
	    	}
	    	return result;
	   }
}
