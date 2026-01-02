package com.hospital.base.core.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.core.features.FeatureEntity;
import com.hospital.base.core.features.FeatureService;

@RestController
@RequestMapping("/api/index/features")
public class IndexController {

	@Autowired
	FeatureService service;

	@GetMapping
	public ResponseEntity<List<FeatureEntity>> getAllFeatures() {
		List<FeatureEntity> list = service.getAllFeatures();

		return new ResponseEntity<List<FeatureEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

}
