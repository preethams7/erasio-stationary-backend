package com.hospital.base.user.profile;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hospital.base.core.account.accounts.bio.AccountBioEntity;
import com.hospital.base.core.account.accounts.bio.AccountBioService;
import com.hospital.base.exceptions.RecordNotFoundException;
import com.hospital.base.user.profile.UserAddress.UserAddressEntity;
import com.hospital.base.user.profile.UserAddress.UserAddressService;
import com.hospital.base.user.subscriptions.AccountSubscriptionEntity;
import com.hospital.base.user.subscriptions.AccountSubscriptionService;



@Service
@Component
public class ProfileService {
	
	@Autowired
	AccountSubscriptionService subSrvc;
	
	@Autowired
	UserAddressService addrSrvc;
	
	@Autowired
	AccountBioService accountBoSrvc;
	

	public List<UserAddressEntity> getDetaultAddress(String username) {
		List<UserAddressEntity> defAddr = addrSrvc.getDefaultCustomerAddress(username);
		return defAddr;
	}


	public List<AccountSubscriptionEntity> getUserSubscriptions(String username) {
		List<AccountSubscriptionEntity> subs = subSrvc.getAccountSubscriptionByUsername(username);
		return subs;
	}


	public AccountBioEntity getUserBio(String username) {
		AccountBioEntity bio = accountBoSrvc.getAccountBioByUsername(username);
		return bio;
	}


	


	public AccountBioEntity updateProfileDO(@Valid ProfileDO account) throws RecordNotFoundException {
			if (account.getUserId() != null) {
				AccountBioEntity bioEntity = new AccountBioEntity();
				if (account!=null) {
					AccountBioEntity bioEntityId = getUserBio(account.getEmail());
					if(bioEntityId!=null) {
						bioEntity.setId(bioEntityId.getId());	
					}else {
						bioEntity.setId(null);
					}
					bioEntity.setCompany(account.getCompany());
					bioEntity.setImageMeta(account.getImageMeta());
					bioEntity.setImage(account.getImage());
					bioEntity.setPhone(account.getPhone());
					bioEntity.setCompany(account.getTaxID());
					bioEntity.setCountry(account.getCountry());
					bioEntity.setTaxid(account.getTaxID());
					bioEntity.setBio(account.getBio());
					bioEntity.setFirstname(account.getName());
					bioEntity.setEmail(account.getEmail());
					
					if(bioEntityId!=null) {
						bioEntity = accountBoSrvc.updateAccountBio(bioEntity);	
					}else{
						bioEntity = accountBoSrvc.createAccount(bioEntity);
					}
					
				}
				return bioEntity;
			}else {
				return null;
			}
		}
	}
	
	
