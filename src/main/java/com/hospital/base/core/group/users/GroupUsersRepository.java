package com.hospital.base.core.group.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface GroupUsersRepository extends JpaRepository<GroupUsersEntity, Long> {

	List<GroupUsersEntity> findByGroupname(String email);
	List<GroupUsersEntity> findByUsername(String email);
	
	

}
