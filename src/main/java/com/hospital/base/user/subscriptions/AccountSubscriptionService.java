package com.hospital.base.user.subscriptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;
import com.hospital.base.user.financial.Payment.UserPaymentEntity;
import com.hospital.base.user.financial.transactions.TransactionEntity;
import com.hospital.base.user.financial.transactions.TransactionService;
import com.hospital.base.utils.DateTimeUtils;

@Service
public class AccountSubscriptionService {

	@Autowired
	AccountSubscriptionRepository repository;

	@Autowired
	DateTimeUtils dtu;

	@Autowired
	TransactionService trnSrvc;

	public AccountSubscriptionService(AccountSubscriptionRepository sessionRepository) {
		this.repository = sessionRepository;
	}

	public List<AccountSubscriptionEntity> getAllAccountSubscrptions(String username) {
		List<AccountSubscriptionEntity> objList = repository.findByUsername(username);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<AccountSubscriptionEntity>();
		}
	}

	public AccountSubscriptionEntity getAccountSubscriptionById(Long id) throws RecordNotFoundException {
		Optional<AccountSubscriptionEntity> objList = repository.findById(id);

		if (objList.isPresent()) {
			return objList.get();
		} else {
			throw new RecordNotFoundException("No AccountSubscription exists for given id", id);
		}
	}

	public List<AccountSubscriptionEntity> getAccountSubscriptionByUsername(String username)
			throws RecordNotFoundException {
		List<AccountSubscriptionEntity> objList = repository.findByUsername(username);

		return objList;
	}

	public AccountSubscriptionEntity createAccountSubscrption(AccountSubscriptionEntity entity)
			throws RecordNotFoundException {
		entity = repository.save(entity);
		return entity;

	}

	public List<AccountSubscriptionEntity> findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public List<AccountSubscriptionEntity> findByExpired(String expired) {
		return repository.findByExpires(expired);
	}

	public List<AccountSubscriptionEntity> findBySubCode(String subcode) {
		return repository.findBySubcode(subcode);
	}

	public List<AccountSubscriptionEntity> findByUsernameAndSubcode(String username, String subcode) {
		return repository.findByUsernameAndSubcode(username, subcode);
	}

	public List<AccountSubscriptionEntity> findByUsernameAndExpired(String username, String expired) {
		return repository.findByUsernameAndExpires(username, expired);
	}

	public AccountSubscriptionEntity createUserSubscription(AccountSubscriptionEntity entity, String username,
			UserPaymentEntity paymentEntity) {

		entity.setUsername(username);

		TransactionEntity transactionEntity = trnSrvc.processTransaction(username, "SUBSCRIPTION", paymentEntity);

		if (transactionEntity.isTransactionStatus()) {
			int length = 12;
			boolean includeLetters = true;
			boolean includeNumbers = true;

			String subCode = "SUB_" + RandomStringUtils.random(length, includeLetters, includeNumbers);
			entity.setSubcode(subCode);
			entity.setTransactionLink(transactionEntity.getId().toString());
			entity = repository.save(entity);

			return entity;
		} else {
			return null;
		}

	}

	public List<AccountSubscriptionEntity> getAllAccountSubscriptionsByState(String username, String state) {
		List<AccountSubscriptionEntity> objList = repository.findByUsername(username);

		List<AccountSubscriptionEntity> filtered = new ArrayList<AccountSubscriptionEntity>();
		if (objList.size() > 0) {
			String today = dtu.getCurrentDate();
			for (int i = 0; i < objList.size(); i++) {
				if ("ACTIVE".equalsIgnoreCase(state)) {
					if ((dtu.dateCompare(today, objList.get(i).getSubdate()) > 0
							&& dtu.dateCompare(objList.get(i).getExpires(), today) > 0)
							|| ((dtu.dateCompare(today, objList.get(i).getSubdate()) == 0
									&& dtu.dateCompare(objList.get(i).getExpires(), today) > 0)
									|| (dtu.dateCompare(today, objList.get(i).getSubdate()) > 0
											&& dtu.dateCompare(objList.get(i).getExpires(), today) == 0))) {
						filtered.add(objList.get(i));
					}

				} else {
					if (dtu.dateCompare(today, objList.get(i).getSubdate()) < 0
							|| dtu.dateCompare(objList.get(i).getExpires(), today) < 0) {
						filtered.add(objList.get(i));
					}
				}
			}

			return filtered;

		} else {
			return new ArrayList<AccountSubscriptionEntity>();
		}
	}

	public List<AccountSubscriptionEntity> getAllSubscrptions() {
		List<AccountSubscriptionEntity> objList = repository.findAll();

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<AccountSubscriptionEntity>();
		}
	}

	public AccountSubscriptionEntity getAccountSubscriptionByIdAndUsername(Long id, String username) {
		AccountSubscriptionEntity objList = repository.findByIdAndUsername(id,username);

		if (objList!=null) {
			return objList;
		} else {
			return new AccountSubscriptionEntity();
		}
	}

}
