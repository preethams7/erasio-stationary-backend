package com.hospital.base.core.group.permissions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_permissions")

public class GroupPermissionEntity {

	public GroupPermissionEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "groupname")
	private String groupname;

	@Column(name = "tx_permission")
	private String permission;
	
	public String toString() {
		return "GroupPermissionEntity [id=" + id + ", groupname=" + groupname + ", permission=" + permission  + "]";
	}
	
	public GroupPermissionEntity(String groupname) {
		this.groupname = groupname;
	}


	public String getPermission() {
		return permission;
	}


	public void setPermission(String permission) {
		this.permission = permission;
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
