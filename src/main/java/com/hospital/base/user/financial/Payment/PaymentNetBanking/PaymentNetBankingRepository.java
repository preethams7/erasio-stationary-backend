package com.hospital.base.user.financial.Payment.PaymentNetBanking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface PaymentNetBankingRepository extends JpaRepository<PaymentNetBankingEntity, Long> {

	
	PaymentNetBankingEntity findByid(Long id);

	PaymentNetBankingEntity findByaccountnumber(int number);
	
	
	
		
	}
