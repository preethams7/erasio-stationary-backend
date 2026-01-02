package com.hospital.base.core.features;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@PreAuthorize("hasAuthority('FEATURE MANAGEMENT')")
@RequestMapping("/api/features")
public class FeatureController {

	@Autowired
	FeatureService service;

	@PreAuthorize("hasAuthority('FEATURE MANAGEMENT')")
	@GetMapping
	public ResponseEntity<List<FeatureEntity>> getAllFeatures() {
		List<FeatureEntity> list = service.getAllFeatures();

		return new ResponseEntity<List<FeatureEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('FEATURE MANAGEMENT')")
	@GetMapping("/{id}")
	public ResponseEntity<FeatureEntity> getFeatureById(@PathVariable("id") Long id) throws RecordNotFoundException {
		FeatureEntity entity = service.getFeatureById(id);

		return new ResponseEntity<FeatureEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('FEATURE MANAGEMENT')")
	@PostMapping("/update")
	public ResponseEntity<FeatureEntity> updateFeature(@Valid @RequestBody FeatureEntity feature)
			throws RecordNotFoundException {
		FeatureEntity updated = service.updateFeatureStatus(feature);
		return new ResponseEntity<FeatureEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}
}
