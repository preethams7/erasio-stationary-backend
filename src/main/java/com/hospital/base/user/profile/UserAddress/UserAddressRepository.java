package com.hospital.base.user.profile.UserAddress;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

	UserAddressEntity findByUsernameAndAddress1(String email, String address);
	
	Optional<UserAddressEntity> findById(Long id);
	
	UserAddressEntity findByCity(String city);

	List<UserAddressEntity> findByUsername(String username);

	UserAddressEntity findByUsernameAndAddressName(String name, String name2);

	List<UserAddressEntity> findByUsernameAndDefaults(String username, boolean defaults);
	
	List<UserAddressEntity> findByUsernameIgnoreCase(String username);

}
