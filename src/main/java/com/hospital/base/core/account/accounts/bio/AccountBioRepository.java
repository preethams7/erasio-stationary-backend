package com.hospital.base.core.account.accounts.bio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface AccountBioRepository extends JpaRepository<AccountBioEntity, Long> {

	AccountBioEntity findByEmail(String email);
	AccountBioEntity findByPhone(String num);
	



}
