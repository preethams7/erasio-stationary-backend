package com.hospital.base.admin.setting.subscriptions.requirement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.core.account.authority.AccountAuthorityEntity;
import com.hospital.base.core.account.authority.AccountAuthorityService;
import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class AccountSubscriptionRequirementService {

	@Autowired
	AccountSubscriptionRequirementRepository repository;

	@Autowired
	AccountAuthorityService authority;

	public AccountSubscriptionRequirementService(AccountSubscriptionRequirementRepository sessionReqRepository) {
		this.repository = sessionReqRepository;
	}

	public List<AccountSubscriptionRequirementEntity> getAllAccountSubscrptionRequirementss() {
		List<AccountSubscriptionRequirementEntity> objList = repository.findAll();

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<AccountSubscriptionRequirementEntity>();
		}
	}

	public AccountSubscriptionRequirementEntity getAccountSubscriptionequirementsById(Long id)
			throws RecordNotFoundException {
		Optional<AccountSubscriptionRequirementEntity> objList = repository.findById(id);

		if (objList.isPresent()) {
			return objList.get();
		} else {
			throw new RecordNotFoundException("No Subscription exists for given id", id);
		}
	}

	public AccountSubscriptionRequirementEntity getAccountSubscriptionRequirementByUsername(String username)
			throws RecordNotFoundException {
		AccountSubscriptionRequirementEntity objList = repository.findByUsernameIgnoreCase(username);

		return objList;
	}

	public AccountSubscriptionRequirementEntity createAccountSubscrptionRequirements(
			AccountSubscriptionRequirementEntity entity) throws RecordNotFoundException {
		entity = repository.save(entity);
		return entity;
	}

	public AccountSubscriptionRequirementEntity updateAccountSubscrptionRequirements(
			AccountSubscriptionRequirementEntity entity) throws RecordNotFoundException {

		List<AccountAuthorityEntity> authorities = authority.getAuthoritiesByUser(entity.getUsername());

		boolean authorityCheck = false;
		for (AccountAuthorityEntity accountAuthorityEntity : authorities) {
			if (accountAuthorityEntity.getRole().equalsIgnoreCase("ROLE_ROOT")
					|| accountAuthorityEntity.getRole().equalsIgnoreCase("ROLE_SUPER")) {
				authorityCheck = true;
			}
		}

		if (authorityCheck) {
			// CANNOT MODIFY
			return entity;
		} else {
			Optional<AccountSubscriptionRequirementEntity> ob = repository.findById(entity.getId());

			if (ob.isPresent()) {
				AccountSubscriptionRequirementEntity newEntity = ob.get();
				newEntity.setRequired(entity.isRequired());
				newEntity = repository.save(newEntity);
				return newEntity;
			} else {
				AccountSubscriptionRequirementEntity newEntity = new AccountSubscriptionRequirementEntity();
				newEntity.setUsername(entity.getUsername());
				newEntity.setRequired(entity.isRequired());
				newEntity = repository.save(newEntity);
				return entity;
			}
		}

	}

}
