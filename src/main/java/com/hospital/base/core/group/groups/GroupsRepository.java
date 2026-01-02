package com.hospital.base.core.group.groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface GroupsRepository extends JpaRepository<GroupsEntity, Long> {

	GroupsEntity findByGroupname(String email);
	
	

}
