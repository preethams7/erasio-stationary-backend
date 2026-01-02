package com.hospital.base.user.financial.Payment.PaymentCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCardEntity, Long> {

	
	PaymentCardEntity findByid(Long id);

	PaymentCardEntity findBycardnumber(int number);
	
	PaymentCardEntity findBycardname(String name);
	
	
		
	}
