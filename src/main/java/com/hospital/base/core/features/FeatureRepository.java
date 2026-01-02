package com.hospital.base.core.features;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {
	FeatureEntity findByFeature(String feature);
}
