package com.hospital.base.core.account.accounts.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class AccountSessionService {

	@Autowired
	AccountSessionRepository repository;
	
	
	@Autowired
	AccountSessionService service;
		
	public AccountSessionService(AccountSessionRepository sessionRepository) {
		this.repository = sessionRepository;
	}

	public List<AccountSessionEntity> getAllAccountSessions() {
		List<AccountSessionEntity> accountSessionList = repository.findAll();

		if (accountSessionList.size() > 0) {
			return accountSessionList;
		} else {
			return new ArrayList<AccountSessionEntity>();
		}
	}

	public AccountSessionEntity getAccountSessionById(Long id) throws RecordNotFoundException {
		Optional<AccountSessionEntity> session = repository.findById(id);

		if (session.isPresent()) {
			return session.get();
		} else {
			throw new RecordNotFoundException("No session exist for given id", id);
		}
	}
	
	public String getAccountLastSessionByUsername(String username) throws RecordNotFoundException {
		List<AccountSessionEntity> session = repository.findByUsername(username);

		if (session!=null && session.size()>0) {
			
			return session.get(session.size()-1).getDatetime();
		} else {
			return "No session found!";
		}
	}

	
	public AccountSessionEntity createAccountSession(AccountSessionEntity entity) throws RecordNotFoundException {
			entity = repository.save(entity);
		return entity;

	}

	 public List<AccountSessionEntity> findByUsername(String username) {
	        return repository.findByUsername(username);
	    }
	 
	
}
