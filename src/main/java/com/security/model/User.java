package com.security.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "roles")
	private String roles;
	
	@Column(name = "statusId")
	private int statusId;
	
	@Column(name = "creation_date")
	private String creationDate;
	
	@Column(name = "lastupdate_date")
	private String lastupdateDate;
	
	@Transient
	List<String> accessRoles;
	
}
