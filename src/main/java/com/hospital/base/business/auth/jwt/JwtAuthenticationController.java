package com.hospital.base.business.auth.jwt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.admin.setting.subscriptions.requirement.AccountSubscriptionRequirementService;
import com.hospital.base.core.account.accounts.AccountsEntity;
import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.core.account.accounts.session.AccountSessionEntity;
import com.hospital.base.core.account.accounts.session.AccountSessionService;
import com.hospital.base.core.account.authority.AccountAuthorityService;
import com.hospital.base.core.features.FeatureService;
import com.hospital.base.exceptions.FeatureDisabledException;
import com.hospital.base.restaurant.restaurant.RestaurantService;
import com.hospital.base.user.subscriptions.AccountSubscriptionService;
import com.hospital.base.utils.DateTimeUtils;

import io.jsonwebtoken.Claims;

@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	@Autowired
//	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private JwtUserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	AccountAuthorityService authService;

	@Autowired
	AccountSessionService sessionService;

	@Autowired
	DateTimeUtils dateTimeUtils;

	@Autowired
	AccountSubscriptionRequirementService subscriptionReqService;

	@Autowired
	AccountSubscriptionService subscriptionService;

	@Autowired
	FeatureService featureService;

	@Autowired
	AccountsService accountsService;

	@Autowired
	RestaurantService restaurantService;

	@RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
			HttpServletResponse response) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		if (featureService.checkFeatureStatuswithToken("LOGIN", token)) {
			final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails.getUsername());
			AccountsEntity account = accountsService.findByUsername(authenticationRequest.getUsername());

			if (!account.isDisabled()) {
				AccountSessionEntity sessionEntity = new AccountSessionEntity();
				String lastSession = sessionService.getAccountLastSessionByUsername(userDetails.getUsername());
				sessionEntity.setUsername(userDetails.getUsername());
				sessionEntity.setDatetime(dateTimeUtils.getCurrentTimeUsingDate());
				sessionService.createAccountSession(sessionEntity);

				ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken).httpOnly(true).secure(false)
						.sameSite("Lax").build();

				response.addHeader(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString());
				return ResponseEntity.ok(new JwtResponse(token, userDetails, lastSession, refreshToken));
			} else {
				throw new FeatureDisabledException("Attention",
						"Account has been disabled. Contact Administrator for support.");

			}

		} else {
			throw new FeatureDisabledException("Attention",
					"Feature has been disabled. Contact Administrator for support.");

		}

	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("api/refresh")
	public ResponseEntity<?> refresh(@CookieValue(name = "refreshToken", required = false) String refreshToken) {
		if (refreshToken == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Claims claims;
		try {
			claims = jwtTokenUtil.validateRefreshToken(refreshToken);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String username = claims.getSubject();
		String newAccessToken = jwtTokenUtil.generateRefreshToken(username);
		return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
	}
	
	@GetMapping("api/me")
    public ResponseEntity<?> me(
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No refresh token");
        }

        try {
           
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(refreshToken);
            String username = claims.getSubject();

            if (username == null || jwtTokenUtil.isTokenExpired(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }

           
            UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(username);

           
            String newAccessToken = jwtTokenUtil.generateToken(userDetails);

           
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("username", userDetails.getUsername());
            response.put("roles", userDetails.getAuthorities());
            response.put("accessToken", newAccessToken);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
	@PostMapping("api/logout")
	public ResponseEntity<?> logout(HttpServletResponse response) {
	    Cookie cookie = new Cookie("refreshToken", null);
	    cookie.setHttpOnly(true);
	    cookie.setSecure(false); 
	    cookie.setPath("/api");
	    cookie.setMaxAge(0); 
	    response.addCookie(cookie);
	    return ResponseEntity.ok("Logged out");
	}

}
