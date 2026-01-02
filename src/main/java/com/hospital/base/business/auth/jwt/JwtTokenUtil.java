package com.hospital.base.business.auth.jwt;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.hospital.base.core.account.authority.AccountAuthorityEntity;
import com.hospital.base.core.account.authority.AccountAuthorityService;
import com.hospital.base.core.account.permissions.AccountPermissionEntity;
import com.hospital.base.core.account.permissions.AccountPermissionService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5*60*60;

	@Autowired 
	AccountAuthorityService authService;
	
	@Autowired 
	AccountPermissionService permService;
	
	@Value("${jwt.secret}")
	private String secret;
	
	private final long ACCESS_EXP = 1000 * 60 * 15; 
    private final long REFRESH_EXP = 1000 * 60 * 60 * 24 * 7; 

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		List<AccountAuthorityEntity> authority = authService.getAuthoritiesByUser(userDetails.getUsername());
		List<AccountPermissionEntity> permissions = permService.getPermissionsByUser(userDetails.getUsername());
		claims.put("authority", authority);
		claims.put("permissions", permissions);
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXP)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String generateRefreshToken(String username) {
		
		return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXP))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
	}
	
	public Claims validateRefreshToken(String token) throws Exception {
	    try {
	        Claims claims = getAllClaimsFromToken(token);

	        String username = claims.getSubject();
	        if (username == null || username.isEmpty()) {
	            throw new Exception("Invalid token: no username");
	        }

	        if (isTokenExpired(token)) {
	            throw new Exception("Refresh token expired");
	        }

	        return claims; // ✅ return claims if valid

	    } catch (Exception e) {
	        throw new Exception("Invalid refresh token");
	    }
	}


}
