package com.hospital.base.user.financial.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hospital.base.core.features.FeatureService;
import com.hospital.base.exceptions.FeatureDisabledException;
import com.hospital.base.user.financial.Payment.UserPaymentEntity;
import com.hospital.base.user.financial.Payment.UserPaymentService;

@Component
@Service
public class TransactionService {

	@Autowired
	TransactionRepository repository;

	@Autowired
	UserPaymentService pmtSrvc;

	@Autowired
	FeatureService featureService;

	public TransactionService(TransactionRepository repo) {
		this.repository = repo;
	}

	public List<TransactionEntity> getAllUserTransactions(String username) {
		List<TransactionEntity> objList = repository.findByUsername(username);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<TransactionEntity>();
		}
	}

	public List<TransactionEntity> getUserTransactionPaymentById(String username, Long id) {
		List<TransactionEntity> objList = repository.findByUsernameAndId(username, id);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<TransactionEntity>();
		}
	}

	public TransactionEntity createTransaction(String username, TransactionEntity entity) {
		entity.setUsername(username);
		entity = repository.save(entity);
		return entity;
	}

	public TransactionEntity processTransaction(String username, String transactionType,
			UserPaymentEntity paymentEntity) {

		paymentEntity.setUsername(username);
		UserPaymentEntity payment = new UserPaymentEntity();
		;
		TransactionEntity tEntity = new TransactionEntity();
		if (featureService.checkFeatureStatus("PAYMENT")) {
			
			
			try {
				payment = pmtSrvc.processPayment(paymentEntity);
				if(payment.getPaymentStatus().equalsIgnoreCase("SUCCESS")) {
					tEntity.setTransactionStatus(true);	
				}else {
					tEntity.setTransactionStatus(false);
				}
				
			} catch (Exception e) {
				tEntity.setTransactionStatus(false);
				e.printStackTrace();
			}

			tEntity.setTransactionType(transactionType);
			tEntity.setUsername(username);
			tEntity.setPaymentLink(payment.getId().toString());
			tEntity = createTransaction(username, tEntity);
			return tEntity;
		} else {
			throw new FeatureDisabledException("Feature has been disabled. Contact Administrator for support.",
					"PAYMENT");
		}
	}

	public List<TransactionEntity> getAllTransactions() {
		List<TransactionEntity> objList = repository.findAll();

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<TransactionEntity>();
		}
	}

	public TransactionEntity getTransactionsById(Long id) {
		Optional<TransactionEntity> obj = repository.findById(id);

		if (obj.isPresent()) {
			return obj.get();
		} else {
			return new TransactionEntity();
		}
	}

	public List<TransactionEntity> getTransactionsByUsername(String username) {
		List<TransactionEntity> objList = repository.findByUsername(username);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<TransactionEntity>();
		}
	}

}
