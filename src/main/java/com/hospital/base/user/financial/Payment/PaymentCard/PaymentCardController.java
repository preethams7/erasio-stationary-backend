package com.hospital.base.user.financial.Payment.PaymentCard;

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
@RequestMapping("/api/user/paymentcard")
public class PaymentCardController {

	@Autowired
	PaymentCardService service;
	
	  @GetMapping
	    public ResponseEntity<List<PaymentCardEntity>> getAllPayment() {
	        List<PaymentCardEntity> list = service.getAllPaymentCard();
	 
	        return new ResponseEntity<List<PaymentCardEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    /**
	     * @param id
	     * @return
	     * @throws RecordNotFoundException
	     * 
	     * describe
	     */
	    @GetMapping("/{id}")
	    public ResponseEntity<PaymentCardEntity> getPaymentCardById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	PaymentCardEntity entity = service.getPaymentCardById(id);
	 
	        return new ResponseEntity<PaymentCardEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/name={name}")
	    public ResponseEntity<PaymentCardEntity> getPaymentCardByUsername(@PathVariable("name") String name)
	                                                    throws RecordNotFoundException {
	    	PaymentCardEntity entity = service.getPaymentCardByCardname(name);
	 
	        return new ResponseEntity<PaymentCardEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    
	    @PostMapping("/create")
	    public ResponseEntity<PaymentCardEntity> createPayment(@Valid @RequestBody PaymentCardEntity entity)
	                                                    throws RecordNotFoundException {
	    	PaymentCardEntity updated = service.createPaymentCard(entity);
	        return new ResponseEntity<PaymentCardEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PostMapping("/update")
	    public ResponseEntity<PaymentCardEntity> updatePaymentCard(@Valid @RequestBody PaymentCardEntity entity)
	                                                    throws RecordNotFoundException {
	    	PaymentCardEntity updated = service.updatePaymentCard(entity);
	        return new ResponseEntity<PaymentCardEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	  
}
