package com.hospital.base.user.financial.Payment.PaymentCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class PaymentCardService {

	@Autowired
	PaymentCardRepository repository;
	
	
	public PaymentCardService(PaymentCardRepository repo) {
		this.repository = repo;
	}

	public List<PaymentCardEntity> getAllPaymentCard() {
		List<PaymentCardEntity> objList = repository.findAll();

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<PaymentCardEntity>();
		}
	}

	public List<PaymentCardEntity> getPaymentCardByCardnumber(int number) {
		List<PaymentCardEntity> objList = (List<PaymentCardEntity>) repository.findBycardnumber(number);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<PaymentCardEntity>();
		}
	}
	
	public PaymentCardEntity getPaymentCardById(Long id) throws RecordNotFoundException {
		Optional<PaymentCardEntity> obj = repository.findById(id);

		if (obj.isPresent()) {
			return obj.get();
		} else {
			throw new RecordNotFoundException("No PaymentCard exist for given id", id);
		}
	}
	
	public PaymentCardEntity getPaymentCardByCardname(String name) throws RecordNotFoundException {
		PaymentCardEntity obj = repository.findBycardname(name);
		
		if (obj!=null) {
			return obj;
		} else {
			throw new RecordNotFoundException("No PaymentCard exist for given name", name);
		}
	}
	
	public PaymentCardEntity createPaymentCard(PaymentCardEntity entity) throws RecordNotFoundException {
			entity = repository.save(entity);
			entity = repository.findByid(entity.getId());
		return entity;
	}
	

	public void deletePaymentCard(PaymentCardEntity entity) throws RecordNotFoundException {
			repository.delete(entity);
	}

	public PaymentCardEntity updatePaymentCard(PaymentCardEntity entity) throws RecordNotFoundException {
		if (entity.getId() != null) {
			Optional<PaymentCardEntity> obj = repository.findById(entity.getId());
			if (obj.isPresent()) {
				PaymentCardEntity newEntity = obj.get();
				newEntity.setCardname(entity.getCardname()!=null?entity.getCardname():obj.get().getCardname());
				newEntity.setCardnumber(entity.getCardnumber()!=null?entity.getCardnumber():obj.get().getCardnumber());
				newEntity.setExpiry(entity.getExpiry()!=null?entity.getExpiry():obj.get().getExpiry());
				newEntity.setCCV(entity.getCCV()!=null?entity.getCCV():obj.get().getCCV());
				newEntity = repository.save(newEntity);
				return newEntity;
			} else {
				return entity;
			}
		}
		throw new RecordNotFoundException("No paymentcard exist for given name", entity.getCardname());
	}
}
