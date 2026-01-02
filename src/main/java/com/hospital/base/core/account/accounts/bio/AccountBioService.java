package com.hospital.base.core.account.accounts.bio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class AccountBioService {

	@Autowired
	AccountBioRepository repository;
	
	
	public AccountBioService(AccountBioRepository userRepository) {
		this.repository = userRepository;
	}

	public List<AccountBioEntity> getAllAccounts() {
		List<AccountBioEntity> accountList = repository.findAll();

		if (accountList.size() > 0) {
	
			return accountList;
		} else {
			return new ArrayList<AccountBioEntity>();
		}
	}

	public AccountBioEntity getAccountBioById(Long id) throws RecordNotFoundException {
		Optional<AccountBioEntity> feature = repository.findById(id);

		if (feature.isPresent()) {
			return feature.get();
		} else {
			throw new RecordNotFoundException("No Bio exist for given id", id);
		}
	}

	public AccountBioEntity updateAccountBio(AccountBioEntity entity) throws RecordNotFoundException {
		if (entity.getId() != null) {
			Optional<AccountBioEntity> bio = repository.findById(entity.getId());
			if (bio.isPresent()) {
				AccountBioEntity newEntity = bio.get();
				newEntity.setBio(entity.getBio()!=null?entity.getBio():bio.get().getBio());
				newEntity.setEmail(entity.getEmail()!=null?entity.getEmail():bio.get().getEmail());
				newEntity.setFirstname(entity.getFirstname()!=null?entity.getFirstname():bio.get().getFirstname());
				newEntity.setLastname(entity.getLastname()!=null?entity.getLastname():bio.get().getLastname());
				newEntity.setPhone(entity.getPhone()!=null?entity.getPhone():bio.get().getPhone());
				newEntity.setWeb(entity.getWeb()!=null?entity.getWeb():bio.get().getWeb());
				newEntity.setImage(entity.getImage()!=null?entity.getImage():bio.get().getImage());
				newEntity.setCountry(entity.getCountry());
				newEntity.setTaxid(entity.getTaxid());
				newEntity = repository.save(newEntity);
				return newEntity;
			} else {
				return entity;
			}
		}
		return entity;
	}

	
	public AccountBioEntity createAccount(AccountBioEntity entity) throws RecordNotFoundException {

			
		AccountBioEntity bio = repository.findByEmail(entity.getEmail());
		
		if(bio==null) {
			entity = repository.save(entity);
		}else {
			updateAccountBio(entity);
		}
		
	
		return entity;

	}

	 public AccountBioEntity findByEmail(String username) {
	        return repository.findByEmail(username);
	    }
	 

	public AccountBioEntity findByPhone(String num) {
		return repository.findByPhone(num);
	}

	public AccountBioEntity getAccountBioByUsername(String username) {
		 return repository.findByEmail(username);
	}


}
