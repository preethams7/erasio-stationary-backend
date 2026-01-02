package com.hospital.base.core.account.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class AccountRoleService {

	@Autowired
	AccountRoleRepository repository;

	public List<AccountRoleEntity> getAllRoles() {
		List<AccountRoleEntity> accountRoleList = repository.findAll();

		if (accountRoleList.size() > 0) {
			return accountRoleList;
		} else {
			return new ArrayList<AccountRoleEntity>();
		}
	}

	public AccountRoleEntity getRoleById(Long id) throws RecordNotFoundException {
		Optional<AccountRoleEntity> role = repository.findById(id);

		if (role.isPresent()) {
			return role.get();
		} else {
			throw new RecordNotFoundException("No Role exist for given id", id);
		}
	}


}
