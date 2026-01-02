package com.hospital.base.core.account.accounts.session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface AccountSessionRepository extends JpaRepository<AccountSessionEntity, Long> {

	List<AccountSessionEntity> findByUsername(String email);
	
	

}
