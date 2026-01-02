package com.hospital.base.user.financial.transactions;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	
	Optional<TransactionEntity> findById(Long id);
	
	List<TransactionEntity> findByTransactionType(String type);

	List<TransactionEntity> findByPaymentLink(String link);
	
	List<TransactionEntity> findByUsername(String username);

	List<TransactionEntity> findByUsernameAndTransactionType(String username, String type);
	
	List<TransactionEntity> findByUsernameAndTransactionTypeAndPaymentLink(String username, String type, String link);

	List<TransactionEntity> findByUsernameAndId(String username, Long id);
}
