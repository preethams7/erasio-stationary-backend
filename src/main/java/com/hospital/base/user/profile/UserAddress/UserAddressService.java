package com.hospital.base.user.profile.UserAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class UserAddressService {

	@Autowired
	UserAddressRepository repository;
	
	public UserAddressService(UserAddressRepository repo) {
		this.repository = repo;
	}

	public List<UserAddressEntity> getAllUserAddress(String username) {
		List<UserAddressEntity> objList = repository.findByUsernameIgnoreCase(username);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<UserAddressEntity>();
		}
	}

	public List<UserAddressEntity> getUserAddressByCity(String city) {
		List<UserAddressEntity> objList = (List<UserAddressEntity>) repository.findByCity(city);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<UserAddressEntity>();
		}
	}
	
	public UserAddressEntity getUserAddressById(Long id) throws RecordNotFoundException {
		Optional<UserAddressEntity> obj = repository.findById(id);

		if (obj.isPresent()) {
			return obj.get();
		} else {
			throw new RecordNotFoundException("No UserAddress exist for given id", id);
		}
	}
	
	public UserAddressEntity getUserAddressByName(String username, String name) throws RecordNotFoundException {
		UserAddressEntity obj = repository.findByUsernameAndAddressName(username,name);
		
		if (obj!=null) {
			return obj;
		} else {
			throw new RecordNotFoundException("No UserAddress exist for given name", name);
		}
	}
	
	public UserAddressEntity createUserAddress(UserAddressEntity entity) throws RecordNotFoundException {
		
		if(entity.isDefaults()) {
		/** Remove defaults from other address entities */
			List<UserAddressEntity> userAddrList = repository.findByUsername(entity.getUsername());
			for (UserAddressEntity item : userAddrList) {
				item.setDefaults(false);
				repository.save(item);
			}
		}
		
			entity = repository.save(entity);
			UserAddressEntity newEntity = repository.findByUsernameAndAddressName(entity.getUsername(), entity.getAddressName());
		return newEntity;
	}
	

	public void deleteUserAddress(UserAddressEntity entity) throws RecordNotFoundException {
			repository.delete(entity);
	}

	public UserAddressEntity updateUserAddress(UserAddressEntity entity) throws RecordNotFoundException {
		if (entity.getId() != null) {
			Optional<UserAddressEntity> obj = repository.findById(entity.getId());
			if (obj.isPresent()) {
				UserAddressEntity newEntity = obj.get();
				newEntity.setAddressName(entity.getAddressName()!=null?entity.getAddressName():obj.get().getAddressName());
				newEntity.setUsername(entity.getUsername()!=null?entity.getUsername():obj.get().getUsername());
				newEntity.setAddress1(entity.getAddress1()!=null?entity.getAddress1():obj.get().getAddress1());
				newEntity.setAddress2(entity.getAddress2()!=null?entity.getAddress2():obj.get().getAddress2());
				newEntity.setAddress3(entity.getAddress3()!=null?entity.getAddress3():obj.get().getAddress3());
				newEntity.setCity(entity.getCity()!=null?entity.getCity():obj.get().getCity());
				newEntity.setPincode(entity.getPincode()!=null?entity.getPincode():obj.get().getPincode());
				newEntity.setState(entity.getState()!=null?entity.getState():obj.get().getState());
				newEntity.setCountry(entity.getCountry()!=null?entity.getCountry():obj.get().getCountry());
				
				/**
				 * Cannot remove default flag from default address
				 */
				if(newEntity.isDefaults() && !entity.isDefaults()) {
					newEntity.setDefaults(obj.get().isDefaults());
				}else {
					/**
					 * Remove default flag from other addresses before setting this address as default
					 */
					if(entity.isDefaults()) {
						List<UserAddressEntity> userAddrList = repository.findByUsername(entity.getUsername()!=null?entity.getUsername():obj.get().getUsername());
						for (UserAddressEntity item : userAddrList) {
							item.setDefaults(false);
							repository.save(item);
						}
					}
					newEntity.setDefaults(entity.isDefaults());
				}
				newEntity = repository.save(newEntity);
				return newEntity;
			} else {
				return entity;
			}
		}
		throw new RecordNotFoundException("No Useraddress exist for given name", entity.getUsername());
	}

	public List<UserAddressEntity> getDefaultCustomerAddress(String username) {
		List<UserAddressEntity> objList = (List<UserAddressEntity>) repository.findByUsernameAndDefaults(username,true);

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<UserAddressEntity>();
		}
		
	}
}
