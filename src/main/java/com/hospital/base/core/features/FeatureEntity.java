package com.hospital.base.core.features;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "features")
public class FeatureEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tx_feature")
	private String feature;
	
	@Column(name = "tx_description")
	private String description;
	
	@Column(name = "fl_active")
	private boolean featureStatus;
	
	public String toString() {
		return "FeatureEntity [id=" + id + ", feature=" + feature+ ", description=" + description
				+ ", status=" + featureStatus+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFeatureStatus() {
		return featureStatus;
	}

	public void setFeatureStatus(boolean featureStatus) {
		this.featureStatus = featureStatus;
	}


}
