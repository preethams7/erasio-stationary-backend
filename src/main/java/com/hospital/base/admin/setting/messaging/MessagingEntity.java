package com.hospital.base.admin.setting.messaging;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messaging")

public class MessagingEntity {

	public MessagingEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "topic")
	private String topic;
	
	@Column(name = "subject")
	private String subject;

	@Column(name = "body", columnDefinition="TEXT")
	private String body;

	@Column(name = "target")
	private String target;
		
	public String toString() {
		return "MessagingEntity [id=" + id + ", topic=" + topic+ ", subject=" + subject
				+ ", body=" + body
				+ "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body ) {
		this.body = body;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}

	}
