package com.library.basic.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(length = 100)
    private String bookName;

    @Column(length = 100)
    private String authorName;

    @Column(length = 50)
    private String bookPublisher;

    private Integer quantity=0;

    @Column(length = 50)
    private String bookType;

    @Column(length = 12)
    private String bookArrived = "NOT ARRIVED";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<BookIssue> bookIssue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requestedStudentId")  // Updated name of the column
    private Student requestedStudent;

//    // Additional @ManyToOne association with Student
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "studentId")
//    private Student student;

    // not required to create setter and getter as we include @Setter @Getter (using lombok dependency)
}
