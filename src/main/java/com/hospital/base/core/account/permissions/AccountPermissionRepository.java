package com.hospital.base.core.account.permissions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface AccountPermissionRepository extends JpaRepository<AccountPermissionEntity, Long> {
	List<AccountPermissionEntity> findByUsername(String username);
}
