package com.hospital.base.core.group.permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface GroupPermissionRepository extends JpaRepository<GroupPermissionEntity, Long> {

	GroupPermissionEntity findByGroupname(String email);
	GroupPermissionEntity findByPermission(String email);
	
	

}
