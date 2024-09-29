package com.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int statusId;
	
	@Column(name = "status_name")
	private String statusName;
	
	@Column(name = "creation_date")
	private String creationDate;
}
