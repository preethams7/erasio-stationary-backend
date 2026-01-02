package com.hospital.base.core.group.groups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system_groups")

public class GroupsEntity {

	public GroupsEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "groupname")
	private String groupname;


	public String toString() {
		return "GroupsEntity [id=" + id + ", groupname=" + groupname + "]";
	}
	
	
	public GroupsEntity(String groupname) {
		this.groupname = groupname;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getGroupname() {
		return groupname;
	}


	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	
}
