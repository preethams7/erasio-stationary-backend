package com.hospital.base.core.account.permissions;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.business.auth.jwt.JwtTokenUtil;
import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.exceptions.FeatureDisabledException;
import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/api/accountPermissions")
public class AccountPermissionController {

	@Autowired
	AccountPermissionService service;

	@Autowired
	AccountPermissionRepository repository;

	@Autowired
	AccountsService accSrvc;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping
	public ResponseEntity<List<AccountPermissionEntity>> getAllPermissions() {
		List<AccountPermissionEntity> list = service.getAllPermissions();

		return new ResponseEntity<List<AccountPermissionEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountPermissionEntity> getPermissionById(@PathVariable("id") Long id)
			throws RecordNotFoundException {
		AccountPermissionEntity entity = service.getPermissionById(id);

		return new ResponseEntity<AccountPermissionEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('USER MANAGEMENT')")
	@GetMapping("/{username}")
	public ResponseEntity<List<AccountPermissionEntity>> getPermissonByUser(@PathVariable("username") String username)
			throws RecordNotFoundException {
		List<AccountPermissionEntity> entity = service.getPermissionsByUser(username);

		return new ResponseEntity<List<AccountPermissionEntity>>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('USER MANAGEMENT')")
	@PostMapping("/update")
	public ResponseEntity<List<AccountPermissionEntity>> createUpdateUserPermissons(
			@Valid @RequestBody AccountPermissionEntity userPermission, HttpServletRequest request)
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

		if (!username.trim().equalsIgnoreCase(userPermission.getUsername().trim())) {

			UserDetails userDetails = accSrvc.loadUserByUsername(userPermission.getUsername());

			Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
			boolean authorizedSuper = authorities.contains(new SimpleGrantedAuthority("ROLE_SUPER"));
			boolean authorizedRoot = authorities.contains(new SimpleGrantedAuthority("ROLE_ROOT"));
			if (!authorizedSuper && !authorizedRoot) {
				if (!service.validateExistance(userPermission)) {
					if (!service.verifyRootSuperPermissionChange(userPermission.getUsername())) {
						repository.save(userPermission);
						List<AccountPermissionEntity> entity = service
								.getPermissionsByUser(userPermission.getUsername());
						return new ResponseEntity<List<AccountPermissionEntity>>(entity, new HttpHeaders(),
								HttpStatus.OK);
					} else {
						List<AccountPermissionEntity> entity = service
								.getPermissionsByUser(userPermission.getUsername());
						return new ResponseEntity<List<AccountPermissionEntity>>(entity, new HttpHeaders(),
								HttpStatus.LOCKED);
					}

				} else {

					List<AccountPermissionEntity> entity = service.getPermissionsByUser(userPermission.getUsername());
					return new ResponseEntity<List<AccountPermissionEntity>>(entity, new HttpHeaders(),
							HttpStatus.MULTI_STATUS);
				}
			} else {
				String role = "";
				if (authorizedSuper)
					role = "SUPER";
				if (authorizedRoot)
					role = "ROOT";
				throw new FeatureDisabledException("Cannot change permissions for specifid user.", role);
			}
		} else {
			throw new FeatureDisabledException("You cannot change permissions for your own account.", username);
		}
	}

	@PreAuthorize("hasAuthority('USER MANAGEMENT')")
	@PostMapping("/delete")
	public ResponseEntity<List<AccountPermissionEntity>> deleteUserPermission(
			@Valid @RequestBody AccountPermissionEntity userPermission, HttpServletRequest request)
			throws RecordNotFoundException {
		UserDetails userDetails = accSrvc.loadUserByUsername(userPermission.getUsername());

		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);

		if (!username.trim().equalsIgnoreCase(userPermission.getUsername().trim())) {

			Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
			boolean authorizedSuper = authorities.contains(new SimpleGrantedAuthority("ROLE_SUPER"));
			boolean authorizedRoot = authorities.contains(new SimpleGrantedAuthority("ROLE_ROOT"));
			if (!authorizedSuper && !authorizedRoot) {

				repository.delete(userPermission);
				List<AccountPermissionEntity> entity = service.getPermissionsByUser(userPermission.getUsername());
				return new ResponseEntity<List<AccountPermissionEntity>>(entity, new HttpHeaders(), HttpStatus.OK);
			} else {
				String role = "";
				if (authorizedSuper)
					role = "SUPER";
				if (authorizedRoot)
					role = "ROOT";
				throw new FeatureDisabledException("Cannot change permissions for specifid user.", role);
			}
		} else {
			throw new FeatureDisabledException("You cannot change permissions for your own account.", username);
		}
	}
}
