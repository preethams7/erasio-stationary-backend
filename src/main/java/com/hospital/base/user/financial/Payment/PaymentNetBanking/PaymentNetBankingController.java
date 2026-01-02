package com.hospital.base.user.financial.Payment.PaymentNetBanking;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/api/user/paymentnetbanking")
public class PaymentNetBankingController {

	@Autowired
	PaymentNetBankingService service;
	
	  @GetMapping
	    public ResponseEntity<List<PaymentNetBankingEntity>> getAllPayment() {
	        List<PaymentNetBankingEntity> list = service.getAllPaymentNetBanking();
	 
	        return new ResponseEntity<List<PaymentNetBankingEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    /**
	     * @param id
	     * @return
	     * @throws RecordNotFoundException
	     * 
	     * describe
	     */
	    @GetMapping("/{id}")
	    public ResponseEntity<PaymentNetBankingEntity> getPaymentCardById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	PaymentNetBankingEntity entity = service.getPaymentNetBankingById(id);
	 
	        return new ResponseEntity<PaymentNetBankingEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/name={name}")
	    public ResponseEntity<PaymentNetBankingEntity> getPaymentNetBankingByUsername(@PathVariable("name") Long name)
	                                                    throws RecordNotFoundException {
	    	PaymentNetBankingEntity entity = service.getPaymentNetBankingById(name);
	 
	        return new ResponseEntity<PaymentNetBankingEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    
	    @PostMapping("/create")
	    public ResponseEntity<PaymentNetBankingEntity> createPaymentNetBanking(@Valid @RequestBody PaymentNetBankingEntity entity)
	                                                    throws RecordNotFoundException {
	    	PaymentNetBankingEntity updated = service.createPaymentNetBanking(entity);
	        return new ResponseEntity<PaymentNetBankingEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PostMapping("/update")
	    public ResponseEntity<PaymentNetBankingEntity> updatePaymentNetBanking(@Valid @RequestBody PaymentNetBankingEntity entity)
	                                                    throws RecordNotFoundException {
	    	PaymentNetBankingEntity updated = service.updatePaymentNetBanking(entity);
	        return new ResponseEntity<PaymentNetBankingEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	  
}
