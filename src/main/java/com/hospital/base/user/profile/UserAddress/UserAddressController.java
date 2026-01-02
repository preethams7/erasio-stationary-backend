package com.hospital.base.user.profile.UserAddress;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.business.auth.jwt.JwtTokenUtil;
import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/api/user/useraddress")
public class UserAddressController {

	@Autowired
	UserAddressService service;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	  @GetMapping
	    public ResponseEntity<List<UserAddressEntity>> getAllUserAddress(HttpServletRequest request) {
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
	        List<UserAddressEntity> list = service.getAllUserAddress(username);
	 
	        return new ResponseEntity<List<UserAddressEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<UserAddressEntity> getUserAddressById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	UserAddressEntity entity = service.getUserAddressById(id);
	 
	        return new ResponseEntity<UserAddressEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/name={name}")
	    public ResponseEntity<UserAddressEntity> getUserAddressByName(HttpServletRequest request, @PathVariable("name") String name)
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
			
	    	UserAddressEntity entity = service.getUserAddressByName(username, name);
	 
	        return new ResponseEntity<UserAddressEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    
	    @PostMapping("/create")
	    public ResponseEntity<UserAddressEntity> createUserAddress(@Valid @RequestBody UserAddressEntity entity)
	                                                    throws RecordNotFoundException {
	    	UserAddressEntity updated = service.createUserAddress(entity);
	        return new ResponseEntity<UserAddressEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PostMapping("/update")
	    public ResponseEntity<UserAddressEntity> updateUserAddress(@Valid @RequestBody UserAddressEntity entity)
	                                                    throws RecordNotFoundException {
	    	UserAddressEntity updated = service.updateUserAddress(entity);
	        return new ResponseEntity<UserAddressEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @DeleteMapping
	    public ResponseEntity<String> deleteUserAddress(@Valid @RequestBody UserAddressEntity entity)
	                                                    throws RecordNotFoundException {
	    	service.deleteUserAddress(entity);
	        return new ResponseEntity<String>("Deleted", new HttpHeaders(), HttpStatus.OK);
	    }
}
