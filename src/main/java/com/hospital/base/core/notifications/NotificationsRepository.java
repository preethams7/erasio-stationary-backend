package com.hospital.base.core.notifications;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Long> {

	List<NotificationsEntity> findByUsername(String username);

	Optional<NotificationsEntity> findByUsernameAndId(String username, Long id);

	List<NotificationsEntity> findByRead(boolean status);

	List<NotificationsEntity> findByUsernameAndRead(String username, boolean status);

}
