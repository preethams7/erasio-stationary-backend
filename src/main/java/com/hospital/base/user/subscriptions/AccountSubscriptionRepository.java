package com.hospital.base.user.subscriptions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface AccountSubscriptionRepository extends JpaRepository<AccountSubscriptionEntity, Long> {

	List<AccountSubscriptionEntity> findByUsername(String email);
	List<AccountSubscriptionEntity> findByUsernameAndSubcode(String name, String subcode);
	List<AccountSubscriptionEntity> findByUsernameAndExpires(String name, String expired);
	List<AccountSubscriptionEntity> findBySubcode(String subcode);
	List<AccountSubscriptionEntity> findByExpires(String expires);
	AccountSubscriptionEntity findByIdAndUsername(Long id, String username);
//	List<AccountSubscriptionEntity> findByUsernameIgnorecase(String username);

}
