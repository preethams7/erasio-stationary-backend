package com.hospital.base.user.financial.transactions;

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
@RequestMapping("/api/user/transactions")
public class TransactionController {

	@Autowired
	TransactionService service;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	  @GetMapping
	    public ResponseEntity<List<TransactionEntity>> getAllUserTransactions(HttpServletRequest request) {
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
	        List<TransactionEntity> list = service.getAllUserTransactions(username);
	 
	        return new ResponseEntity<List<TransactionEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    /**
	     * @param id
	     * @return
	     * @throws RecordNotFoundException
	     * 
	     * describe
	     */
	    @GetMapping("/{id}")
	    public ResponseEntity<List<TransactionEntity>> getUserTransactionById(@PathVariable("id") Long id, HttpServletRequest request)
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
	    	List<TransactionEntity> entity = service.getUserTransactionPaymentById(username, id);
	 
	        return new ResponseEntity<List<TransactionEntity>>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
}
