package com.hospital.base.core.account.accounts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.base.business.auth.UserAuthorityServiceImpl;
import com.hospital.base.core.account.authority.AccountAuthorityEntity;
import com.hospital.base.core.account.authority.AccountAuthorityRepository;
import com.hospital.base.core.account.authority.AccountAuthorityService;
import com.hospital.base.core.account.permissions.AccountPermissionEntity;
import com.hospital.base.core.account.permissions.AccountPermissionService;
import com.hospital.base.exceptions.RecordNotFoundException;
import com.hospital.base.restaurant.restaurant.Restaurant;
import com.hospital.base.restaurant.restaurant.RestaurantService;
import com.hospital.base.utils.DateTimeUtils;

@Service
public class AccountsService implements UserDetailsService {

	@Autowired
	AccountsRepository repository;
	
	@Autowired
	AccountAuthorityRepository authRepository;

	@Autowired 
	AccountPermissionService permService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	AccountsService service;
	

	@Autowired 
	AccountAuthorityService authService;
	
	@Autowired
	UserAuthorityServiceImpl authSrvImpl;
	
	@Autowired
	AccountAuthorityService auth;
	
	@Autowired
	RestaurantService restaurantService;

	public AccountsService(AccountsRepository userRepository) {
		this.repository = userRepository;
	}

	public List<AccountsEntity> getAllAccounts() {
		List<AccountsEntity> accountList = repository.findAll();

		if (accountList.size() > 0) {
			
			accountList.forEach(acc -> {
				acc.setRoles(auth.getAuthoritiesByUser(acc.getUsername()));
				});
			
			return accountList;
		} else {
			return new ArrayList<AccountsEntity>();
		}
	}

	public AccountsEntity getAccountById(Long id) throws RecordNotFoundException {
		Optional<AccountsEntity> feature = repository.findById(id);

		if (feature.isPresent()) {
			return feature.get();
		} else {
			throw new RecordNotFoundException("No account exist for given id", id);
		}
	}

	public AccountsEntity updateAccount(AccountsEntity entity) throws RecordNotFoundException {
		if (entity.getId() != null) {
			Optional<AccountsEntity> feature = repository.findById(entity.getId());
			if (feature.isPresent()) {
				AccountsEntity newEntity = feature.get();
				newEntity.setDisabled(entity.isDisabled());
				newEntity.setTempAuthCode(entity.getTempAuthCode());
				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				return entity;
			}
		}
		return entity;
	}

	
	public AccountsEntity updateAccountPassword(AccountsEntity entity) throws RecordNotFoundException {
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		entity = repository.save(entity);
		return entity;
	}
	
	public AccountsEntity createAccount(AccountsEntity entity) throws RecordNotFoundException {
		
		    entity.setUsername(entity.getUsername());
     		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			entity.setTempAuthCode(UUID.randomUUID().toString());
			entity.setCreatedTime(new DateTimeUtils().getCurrentDateTime());
			entity.setRole(entity.getRole());
			entity = repository.save(entity);	
			AccountAuthorityEntity authEntity = new AccountAuthorityEntity();
			authEntity.setUsername(entity.getUsername());
			authEntity.setRole(entity.getRole());
			authRepository.save(authEntity);
		    return entity;

	}

	 public AccountsEntity findByUsername(String username) {
	        return repository.findByUsername(username);
	    }
	 
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AccountsEntity user = repository.findByUsernameAndDisabled(email, false);
		if (user == null) {
			throw new UsernameNotFoundException("User is not Found");
		}
		List<AccountAuthorityEntity> authority = authService.getAuthoritiesByUser(user.getUsername());
		List<AccountPermissionEntity> permission= permService.getPermissionsByUser(user.getUsername());
		Collection<SimpleGrantedAuthority> auth =  authSrvImpl.mapAuthorities(authority, permission);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				auth);
	}

	public AccountsEntity findByTempAuthCode(String resetToken) {
		return repository.findByTempAuthCode(resetToken);
	}

	public @Valid AccountsEntity clearToken(@Valid AccountsEntity account) {
		account.setTempAuthCode("");
		account.setDisabled(true);
		account = repository.save(account);
		return account;
	}

	public AccountsEntity findByTempAuthCodeAndUsername(String token, String username) {
		return repository.findByTempAuthCodeAndUsername(token,username);
		 
	}
	public AccountsEntity validateEmail(AccountsEntity entity){
		entity.setValidated(true);
		entity.setTempAuthCode("");
		entity = repository.save(entity);
		return entity;
	}
}
