package com.hospital.base.core.account.authority;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class AccountAuthorityService {

	@Autowired
	AccountAuthorityRepository repository;

	public List<AccountAuthorityEntity> getAllAuthorities() {
		List<AccountAuthorityEntity> authorityList = repository.findAll();

		if (authorityList.size() > 0) {
			return authorityList;
		} else {
			return new ArrayList<AccountAuthorityEntity>();
		}
	}

	public AccountAuthorityEntity getAuthorityById(Long id) throws RecordNotFoundException {
		Optional<AccountAuthorityEntity> authority = repository.findById(id);

		if (authority.isPresent()) {
			return authority.get();
		} else {
			throw new RecordNotFoundException("No authority exist for given id", id);
		}
	}

	public List<AccountAuthorityEntity> getAuthoritiesByUser(String username) throws RecordNotFoundException {
		List<AccountAuthorityEntity> authority = repository.findByUsername(username);

		if (authority!=null) {
			return authority;
		} else {
			throw new RecordNotFoundException("No authority exist for given user", username);
		}
	}
	
	public AccountAuthorityEntity createOrUpdateAuthority(AccountAuthorityEntity entity) throws RecordNotFoundException {

		if (entity.getId() != null) {
			Optional<AccountAuthorityEntity> authority = repository.findById(entity.getId());

			if (authority.isPresent()) {
				AccountAuthorityEntity newEntity = authority.get();
				newEntity.setRole(entity.getRole());
				newEntity.setUsername(entity.getUsername());
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}

		else {
			entity = repository.save(entity);
			return entity;
		}
	}

}
