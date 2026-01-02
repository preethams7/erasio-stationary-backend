package com.hospital.base.core.account.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.core.account.authority.AccountAuthorityEntity;
import com.hospital.base.core.account.authority.AccountAuthorityService;
import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class AccountPermissionService {

	@Autowired
	AccountPermissionRepository repository;
	
	@Autowired
	AccountAuthorityService authService;
	
	@Autowired
	AccountsService accService;

	public List<AccountPermissionEntity> getAllPermissions() {
		List<AccountPermissionEntity> accountPermissionList = repository.findAll();

		if (accountPermissionList.size() > 0) {
			return accountPermissionList;
		} else {
			return new ArrayList<AccountPermissionEntity>();
		}
	}

	public AccountPermissionEntity getPermissionById(Long id) throws RecordNotFoundException {
		Optional<AccountPermissionEntity> permission = repository.findById(id);

		if (permission.isPresent()) {
			return permission.get();
		} else {
			throw new RecordNotFoundException("No Permission exist for given id", id);
		}
	}

	public List<AccountPermissionEntity> getPermissionsByUser(String username) throws RecordNotFoundException {
		List<AccountPermissionEntity> permissions = repository.findByUsername(username);

		if (permissions != null) {
			return permissions;
		} else {
			throw new RecordNotFoundException("No Permission exist for given user", username);
		}
	}

	public AccountPermissionEntity createOrUpdateAuthority(AccountPermissionEntity entity)
			throws RecordNotFoundException {

		if (entity.getId() != null) {
			Optional<AccountPermissionEntity> permissions = repository.findById(entity.getId());

			if (permissions.isPresent()) {
				AccountPermissionEntity newEntity = permissions.get();
				newEntity.setPermission(entity.getPermission()!=null?entity.getPermission():permissions.get().getPermission());
				newEntity.setUsername(entity.getUsername()!=null?entity.getUsername():permissions.get().getUsername());
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);
				return entity;
			}
		} else {
			entity = repository.save(entity);
			return entity;
		}
	}

	public boolean validateExistance(@Valid AccountPermissionEntity userPermission) {
		List<AccountPermissionEntity> userPermissions = getPermissionsByUser(userPermission.getUsername());

		for (AccountPermissionEntity perm : userPermissions) {
			if (perm.getPermission().equals(userPermission.getPermission())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyRootSuperPermissionChange(String userName) {
		
		List<AccountAuthorityEntity> roles = authService.getAuthoritiesByUser(userName);
		for (AccountAuthorityEntity role : roles) {
			if (role.getRole().equals("ROLE_ROOT") || role.getRole().equals("ROLE_SUPER") ) {
				return true;
			}
		}
		return false;
		
	}

}
