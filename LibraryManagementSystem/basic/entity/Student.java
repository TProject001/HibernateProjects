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
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer studentId;
	@Column(length=50)
	private String studentName;
	@Column(length=50)
	private String studentEmail;
	@Column(length=14)
	private String studentContactNo;
	@Column(length=50)
	private String studentAddress;
	@Column(length=20)
	private String password;
	@Column(length=10)
	private String accoutStatus="DEACTIVE";
	@Column
	private Double studentTotalFine=0.0;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="student")
	private List<BookIssue> bookIssues ;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="requestedStudent")
	private List<Book> requestedBooks;
	//not require to create setter and getter 
	// as we include @Setter @Getter (using lombok dependency)
	
}
