package com.hospital.base.core.payments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired 
	PaymentService service;
	
//	@Value("${RAZORPAY_KEY_ID}")
	private String razorPayKey;
	
//	@Value("${RAZORPAY_KEY_SECRET}")
	private String razorPaySecret;
	
    @GetMapping("/")
    public List<PaymentEntity> getSubscriptions(){
		return null;
    	
    }

    @GetMapping("/getKey")
    public String getKey(){
		return razorPayKey;
    	
    }
    
    
 /*  @PostMapping("/createOrder")
    public ResponseEntity<PaymentOrderDO> createOrder(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody PaymentDO paymentOrder) throws ServletException, IOException {
    	
    	//Order Creation
    	RazorpayClient client = null;
    	String orderId = null;
    	PaymentOrderDO obj = new PaymentOrderDO() ;
    	
    	try {
    		client = new RazorpayClient(razorPayKey,razorPaySecret);
    		
    		JSONObject options = new JSONObject();
    		options.put("amount", paymentOrder.getAmount());
    		options.put("currency", paymentOrder.getCurrency());
    		options.put("receipt", paymentOrder.getReceipt());
    		options.put("payment_capture", true);
    		Order order = client.Orders.create(options);
    		orderId = order.get("id");
    		obj.setOrderId(orderId);
    	}catch(RazorpayException e) {
    		e.printStackTrace();
    	}
    	return new ResponseEntity<PaymentOrderDO>(obj, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping("/processPayment")
    public String processPayment(HttpServletRequest request, HttpServletResponse response,  @Valid @RequestBody PaymentDO paymentProcessDO) throws ServletException, IOException {
    	//Signature Verification
    	
    	if(service.paymentprocess(paymentProcessDO)) {
    		return "Payment Successful and Signature Verified!";
    	}else {
    		return  "Payment Failed and Signature not Verified!";
    	}
    			
    }*/
}
