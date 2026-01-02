package com.hospital.base.core.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hospital.base.business.auth.jwt.JwtTokenUtil;
import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class FeatureService {

	@Autowired
	FeatureRepository repository;
	
	@Autowired
	JwtTokenUtil jwt;
	
	@Autowired AccountsService accSrvc;

	public List<FeatureEntity> getAllFeatures() {
		List<FeatureEntity> featureList = repository.findAll();

		if (featureList.size() > 0) {
			return featureList;
		} else {
			return new ArrayList<FeatureEntity>();
		}
	}

	public FeatureEntity getFeatureById(Long id) throws RecordNotFoundException {
		Optional<FeatureEntity> feature = repository.findById(id);

		if (feature.isPresent()) {
			return feature.get();
		} else {
			throw new RecordNotFoundException("No feature exist for given id", id);
		}
	}

	public FeatureEntity updateFeatureStatus(FeatureEntity entity) throws RecordNotFoundException {

		if (entity.getId() != null) {
			Optional<FeatureEntity> feature = repository.findById(entity.getId());

			if (feature.isPresent()) {
				FeatureEntity newEntity = feature.get();
				newEntity.setFeatureStatus(entity.isFeatureStatus());
				newEntity = repository.save(newEntity);

				return newEntity;
			}else {
				return entity;
			}
		}else if (entity.getFeature()!= null) {
			FeatureEntity feature = repository.findByFeature(entity.getFeature());

			if (feature!=null) {
				
				feature.setFeatureStatus(entity.isFeatureStatus());
				feature = repository.save(feature);

				return feature;
			}else {
				return entity;
			}
			
		}
		return entity;

	}
	
	public boolean checkFeatureStatuswithToken(String featureName, String jwtToken) {
		FeatureEntity feature = repository.findByFeature(featureName);
		Map<String, Object> claims = jwt.getAllClaimsFromToken(jwtToken);
		UserDetails userDetails = accSrvc.loadUserByUsername(jwt.getUsernameFromToken(jwtToken));
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		boolean authorizedSuper = authorities.contains(new SimpleGrantedAuthority("ROLE_SUPER"));
		if(!authorizedSuper ) {
			return feature.isFeatureStatus();	
		}else {
			return true;
		}
		
	}

	public boolean checkFeatureStatus(String featureName) {
		FeatureEntity feature = repository.findByFeature(featureName);
		return feature.isFeatureStatus();
	}

}
