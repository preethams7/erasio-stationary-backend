package com.hospital.base.core.account.authority;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface AccountAuthorityRepository extends JpaRepository<AccountAuthorityEntity, Long> {
	List<AccountAuthorityEntity> findByUsername(String username);
}
