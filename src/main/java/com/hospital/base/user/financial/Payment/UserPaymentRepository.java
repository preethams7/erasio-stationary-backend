package com.hospital.base.user.financial.Payment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface UserPaymentRepository extends JpaRepository<UserPaymentEntity, Long> {
	
	Optional<UserPaymentEntity> findById(Long id);
	
	List<UserPaymentEntity> findByUsername(String username);

	Optional<UserPaymentEntity> findByIdAndUsername(Long id, String username);

	List<UserPaymentEntity> findByPaymentStatus(String state);

	List<UserPaymentEntity> findByUsernameAndPaymentStatus(String username, String state);

}
