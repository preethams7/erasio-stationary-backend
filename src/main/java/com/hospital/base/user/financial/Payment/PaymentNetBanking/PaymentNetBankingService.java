package com.hospital.base.user.financial.Payment.PaymentNetBanking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class PaymentNetBankingService {

	@Autowired
	PaymentNetBankingRepository repository;
	
	
	public PaymentNetBankingService(PaymentNetBankingRepository repo) {
		this.repository = repo;
	}

	public List<PaymentNetBankingEntity> getAllPaymentNetBanking() {
		List<PaymentNetBankingEntity> objList = repository.findAll();

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<PaymentNetBankingEntity>();
		}
	}

	public List<PaymentNetBankingEntity> getPaymentNetBankingByAccountnumber(int number) {
		@SuppressWarnings("unchecked")
		List<PaymentNetBankingEntity> objList = (List<PaymentNetBankingEntity>) repository.findByaccountnumber(number);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<PaymentNetBankingEntity>();
		}
	}
	
	public PaymentNetBankingEntity getPaymentNetBankingById(Long id) throws RecordNotFoundException {
		Optional<PaymentNetBankingEntity> obj = repository.findById(id);

		if (obj.isPresent()) {
			return obj.get();
		} else {
			throw new RecordNotFoundException("No PaymentNetBanking exist for given id", id);
		}
	}
	
	
	public PaymentNetBankingEntity createPaymentNetBanking(PaymentNetBankingEntity entity) throws RecordNotFoundException {
			entity = repository.save(entity);
			entity = repository.findByid(entity.getId());
		return entity;
	}
	

	public void deletePaymentNetBanking(PaymentNetBankingEntity entity) throws RecordNotFoundException {
			repository.delete(entity);
	}

	public PaymentNetBankingEntity updatePaymentNetBanking(PaymentNetBankingEntity entity) throws RecordNotFoundException {
		if (entity.getId() != null) {
			Optional<PaymentNetBankingEntity> obj = repository.findById(entity.getId());
			if (obj.isPresent()) {
				PaymentNetBankingEntity newEntity = obj.get();
				newEntity.setAccountnumber(entity.getAccountnumber()!=null?entity.getAccountnumber():obj.get().getAccountnumber());
				newEntity.setRoutingIFSCSwift(entity.getRoutingIFSCSwift()!=null?entity.getRoutingIFSCSwift():obj.get().getRoutingIFSCSwift());
				newEntity = repository.save(newEntity);
				return newEntity;
			} else {
				return entity;
			}
		}
		throw new RecordNotFoundException("No PaymentNetBanking exist for given name", entity.getId());
	}
}
