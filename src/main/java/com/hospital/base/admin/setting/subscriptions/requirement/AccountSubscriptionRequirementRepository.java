package com.hospital.base.admin.setting.subscriptions.requirement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface AccountSubscriptionRequirementRepository extends JpaRepository<AccountSubscriptionRequirementEntity, Long> {

	AccountSubscriptionRequirementEntity findByUsername(String email);
	AccountSubscriptionRequirementEntity findByUsernameAndRequired(String name, boolean required);
	List<AccountSubscriptionRequirementEntity> findByRequired(boolean required);
	
	AccountSubscriptionRequirementEntity findByUsernameIgnoreCase(String username);
	

}
