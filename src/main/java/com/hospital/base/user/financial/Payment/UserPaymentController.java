package com.hospital.base.user.financial.Payment;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.business.auth.jwt.JwtTokenUtil;
import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/api/user/userpayment")
public class UserPaymentController {

	@Autowired
	UserPaymentService service;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	  @GetMapping
	    public ResponseEntity<List<UserPaymentEntity>> getAllUserPayment(HttpServletRequest request) {
		  final String requestTokenHeader = request.getHeader("Authorization");
		  String username = null ;
		  String jwtToken = null;
		  if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			  jwtToken = requestTokenHeader.substring(7);
			  username = jwtTokenUtil.getUsernameFromToken(jwtToken);	  
		  }else {
			  Principal principal = request.getUserPrincipal();
		      username =  principal.getName();
		  }
	        List<UserPaymentEntity> list = service.getAllUserPayment(username);
	 
	        return new ResponseEntity<List<UserPaymentEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    /**
	     * @param id
	     * @return
	     * @throws RecordNotFoundException
	     * 
	     * describe
	     */
	    @GetMapping("/{id}")
	    public ResponseEntity<UserPaymentEntity> getPaymentById(@PathVariable("id") Long id, HttpServletRequest request)
	                                                    throws RecordNotFoundException {
	    	 final String requestTokenHeader = request.getHeader("Authorization");
			  String username = null ;
			  String jwtToken = null;
			  if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				  jwtToken = requestTokenHeader.substring(7);
				  username = jwtTokenUtil.getUsernameFromToken(jwtToken);	  
			  }else {
				  Principal principal = request.getUserPrincipal();
			      username =  principal.getName();
			  }
				
	    	UserPaymentEntity entity = service.getPaymentById(id, username);
	 
	        return new ResponseEntity<UserPaymentEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
}
