package com.hospital.base.admin.setting.messaging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface MessagingRepository extends JpaRepository<MessagingEntity, Long> {

	MessagingEntity findByTopic(String name);
	
	MessagingEntity findByTopicIgnoreCase(String name);

}
