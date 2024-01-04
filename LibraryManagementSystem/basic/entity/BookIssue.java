package com.library.basic.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class BookIssue {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer issueId;
	private LocalDate issueDate;
	private LocalDate dueDate ;
	private LocalDate returnDate;
	@Column(length=10)
	private String bookStatus="NOT ISSUE";
	private Double fine=0.0;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="studentId")
	private Student student;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bookId")
	private Book book;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="issueAdminId")
	private Admin bookIssueAdmin;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="returnAdminId")
	private Admin bookReturnAdmin;
	
	//not require to create setter and getter 
	// as we include @Setter @Getter (using lombok dependency)
}
