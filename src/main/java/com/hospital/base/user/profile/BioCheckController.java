package com.hospital.base.user.profile;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.business.auth.jwt.JwtTokenUtil;
import com.hospital.base.core.account.accounts.bio.AccountBioEntity;
import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/api/user/bio-check")
public class BioCheckController {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	ProfileService service;
	
	@GetMapping
	public boolean getUserProfile(HttpServletRequest request) throws RecordNotFoundException {
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
		
		AccountBioEntity bio = new AccountBioEntity();
		bio = service.getUserBio(username);
		
		if (bio!=null) {
			return true;
		}else {
			return false;
		}
		
	}
}
