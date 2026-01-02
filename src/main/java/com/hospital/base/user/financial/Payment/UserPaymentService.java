package com.hospital.base.user.financial.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.core.payments.PaymentService;
import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class UserPaymentService {

	@Autowired
	UserPaymentRepository repository;
	
	@Autowired
	PaymentService paymentSrvc;

	public UserPaymentService(UserPaymentRepository repo) {
		this.repository = repo;
	}

	public List<UserPaymentEntity> getAllPayment() {
		List<UserPaymentEntity> objList = repository.findAll();

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<UserPaymentEntity>();
		}
	}

	public List<UserPaymentEntity> getAllUserPayment(String username) {
		List<UserPaymentEntity> objList = repository.findByUsername(username);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<UserPaymentEntity>();
		}
	}



	public UserPaymentEntity getPaymentById(Long id, String username) throws RecordNotFoundException {
		Optional<UserPaymentEntity> obj = repository.findByIdAndUsername(id, username);

		if (obj.isPresent()) {
			return obj.get();
		} else {
			throw new RecordNotFoundException("No UserPayment exist for given id", id);
		}
	}

	public UserPaymentEntity createPaymentRecord(UserPaymentEntity entity) throws RecordNotFoundException {
		entity = repository.save(entity);
		return entity;
	}

	public void deletePayment(UserPaymentEntity entity) throws RecordNotFoundException {
		repository.delete(entity);
	}

	public UserPaymentEntity updatePayment(UserPaymentEntity entity) throws RecordNotFoundException {
		if (entity.getId() != null) {
			Optional<UserPaymentEntity> obj = repository.findById(entity.getId());
			if (obj.isPresent()) {
				UserPaymentEntity newEntity = obj.get();
				newEntity.setUsername(entity.getUsername() != null ? entity.getUsername() : obj.get().getUsername());
				newEntity = repository.save(newEntity);
				return newEntity;
			} else {
				return entity;
			}
		}
		throw new RecordNotFoundException("No UserPayment exist for given name", entity.getUsername());
	}

	public UserPaymentEntity processPayment(UserPaymentEntity paymentEntity) throws Exception {
		boolean status = false;
		PaymentDO pdo = new PaymentDO();
		pdo = processPaymentDO(paymentEntity);
		status = paymentSrvc.paymentprocess(pdo);
		
		if (!status) {
			paymentEntity.setPaymentStatus("FAILURE");
			UserPaymentEntity payment = createPaymentRecord(paymentEntity);
			throw new Exception("Payment failure");
		} else {
			paymentEntity.setPaymentStatus("SUCCESS");
			UserPaymentEntity payment = createPaymentRecord(paymentEntity);
			return payment;
		}
	}

	private PaymentDO processPaymentDO(UserPaymentEntity paymentEntity) {
		PaymentDO pdo = new PaymentDO();
		pdo.setRazorpay_payment_id(paymentEntity.getRazorpay_payment_id());
		pdo.setRazorpay_order_id(paymentEntity.getRazorpay_order_id());
		pdo.setRazorpay_signature(paymentEntity.getRazorpay_signature());
		return pdo;
	}

	public UserPaymentEntity getPaymentById(Long id) {
		Optional<UserPaymentEntity> obj = repository.findById(id);

		if (obj.isPresent()) {
			return obj.get();
		} else {
			throw new RecordNotFoundException("No  UserPayment exist for given id", id);
		}
	}

	public List<UserPaymentEntity> getPaymentByUsername(String username) {
		List<UserPaymentEntity> obj = repository.findByUsername(username);

		if (obj!=null) {
			return obj;
		} else {
			throw new RecordNotFoundException("No  UserPayment exist for given username",username);
		}
	}

	public List<UserPaymentEntity> getPaymentByStatus(String state) {
		List<UserPaymentEntity> obj = repository.findByPaymentStatus(state);

		if (obj!=null) {
			return obj;
		} else {
			throw new RecordNotFoundException("No  UserPayment exist for given status",state);
		}
	}

	public List<UserPaymentEntity> getPaymentByUsernameAndStatus(String username, String state) {
		List<UserPaymentEntity> obj = repository.findByUsernameAndPaymentStatus(username,state);

		if (obj!=null) {
			return obj;
		} else {
			throw new RecordNotFoundException("No  UserPayment exist for given username and status ",username+":"+state);
		}
	}

}
