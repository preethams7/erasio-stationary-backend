package com.hospital.base.user.profile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.business.auth.jwt.JwtTokenUtil;
import com.hospital.base.core.account.accounts.AccountsEntity;
import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.core.account.accounts.bio.AccountBioEntity;
import com.hospital.base.core.account.accounts.session.AccountSessionEntity;
import com.hospital.base.core.account.accounts.session.AccountSessionService;
import com.hospital.base.core.notifications.NotificationsEntity;
import com.hospital.base.core.notifications.NotificationsService;
import com.hospital.base.exceptions.RecordNotFoundException;


@RestController
@RequestMapping("/api/user/profile")
public class ProfileController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	ProfileService service;
	
	@Autowired
	AccountSessionService sessionService;
	
	@Autowired
	AccountsService accSrvc;
	
	@Autowired
	NotificationsService notifSrvc;

	@GetMapping
	public ResponseEntity<ProfileDO> getUserProfile(HttpServletRequest request) throws RecordNotFoundException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		ProfileDO pdo = new ProfileDO();

		pdo.setDefaultAddress(service.getDetaultAddress(username));
		pdo.setSubscriptions(service.getUserSubscriptions(username));
		
		AccountBioEntity bio = new AccountBioEntity();
		bio = service.getUserBio(username);
		if(bio!=null) {
			pdo.setEmail(bio.getEmail()!=null?bio.getEmail():"");
			pdo.setPhone(bio.getPhone()!=null?bio.getPhone():"");
			pdo.setImage(bio.getImage()!=null?bio.getImage():null);
			pdo.setImageMeta(bio.getImageMeta()!=null?bio.getImageMeta():"");
			pdo.setName(bio.getFirstname()!=null?bio.getFirstname():""+" "+bio.getLastname()!=null?bio.getLastname():"");
			pdo.setCompany(bio.getCompany()!=null?bio.getCompany():"");
			pdo.setCountry(bio.getCountry());
			pdo.setTaxID(bio.getTaxid());
			pdo.setBio(bio.getBio());
		}else {
			pdo.setEmail("");
			pdo.setPhone("");
			pdo.setImage(null);
			pdo.setImageMeta("");
			pdo.setName(""+" "+"");
			pdo.setCompany("");
			pdo.setCountry("");
			pdo.setTaxID("");
		}
		
		
		AccountsEntity acc= accSrvc.findByUsername(username);
		pdo.setUserId(acc.getId());
		pdo.setEmail(username);
		
		AccountSessionEntity sessionEntity = new AccountSessionEntity();
		String lastSession = sessionService.getAccountLastSessionByUsername(username);
		pdo.setLastLogin(lastSession);
		
		List<NotificationsEntity> notifs = notifSrvc.getAllUserNotificationsByUsernameAndReadStatus(username, false);
		if(notifs==null) {
			pdo.setNotifications((long) 0);
		}else {
			pdo.setNotifications((long) notifs.size());
		}
		

		return new ResponseEntity<ProfileDO>(pdo, new HttpHeaders(), HttpStatus.OK);

	}
	@PostMapping("/update")
    public ResponseEntity<ProfileDO> updateProfileDO(@Valid @RequestBody ProfileDO account, HttpServletRequest request)
                                                    throws RecordNotFoundException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		account.setEmail(username);
		
		AccountBioEntity bioEntity = new AccountBioEntity();
		bioEntity = service.updateProfileDO(account);
		
		ProfileDO pdo = new ProfileDO();
		
		/*
		 * pdo.setUserId(bioEntity.getId()); pdo.setCompany(bioEntity.getCompany());
		 * pdo.setCountry(bioEntity.getCountry()); pdo.setEmail(bioEntity.getEmail());
		 * pdo.setImage(bioEntity.getImage());
		 * pdo.setImageMeta(bioEntity.getImageMeta());
		 * pdo.setPhone(bioEntity.getPhone()); pdo.setTaxID(bioEntity.getTaxid());
		 * 
		 * pdo.setName(null); pdo.setNotifications(null); pdo.setLastLogin(null);
		 * pdo.setSubscriptions(null);
		 */
		
		
        return getUserProfile(request);
    }

}
