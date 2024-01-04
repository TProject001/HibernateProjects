package com.library.basic.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminId;
	@Column(length=50)
	private String adminName;
	@Column(length=50)
	private String adminEmail;
	@Column(length=14)
	private String adminContactNo;
	@Column(length=100)
	private String adminAddress;
	@Column(length=20)
	private String adminPassword;
	@Column(length=10)
	private String accoutStatus="DEACTIVE";
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="bookIssueAdmin")
	private List<BookIssue> bookIssues ;
	
	//not require to create setter and getter 
	// as we include @Setter @Getter (using lombok dependency)
	
}
