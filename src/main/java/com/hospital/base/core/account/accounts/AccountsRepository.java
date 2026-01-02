package com.hospital.base.core.account.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface AccountsRepository extends JpaRepository<AccountsEntity, Long> {

	AccountsEntity findByUsernameAndDisabled(String email, boolean disabled);
	
	AccountsEntity findByUsername(String email);
	
	AccountsEntity findByTempAuthCode(String resetToken);

	AccountsEntity findByTempAuthCodeAndUsername(String token, String username);

}
