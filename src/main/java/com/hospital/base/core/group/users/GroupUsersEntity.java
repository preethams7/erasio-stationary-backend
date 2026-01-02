package com.hospital.base.core.group.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_users")

public class GroupUsersEntity {

	public GroupUsersEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "groupname")
	private String groupname;

	@Column(name = "username")
	private String username;
	
	public String toString() {
		return "GroupUsersEntity [id=" + id + ", groupname=" + groupname + ", username=" + username  + "]";
	}
	
	public GroupUsersEntity(String groupname) {
		this.groupname = groupname;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	
}
