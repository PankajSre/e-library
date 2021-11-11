package com.iiht.training.elibrary.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookIssueDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long bookId;
	private Long studentId;
	private LocalDate issueDate;
	private LocalDate expectedReturnedDate;
	private LocalDate actualReturnedDate;
	private Integer fine;
	private Boolean isReturned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getExpectedReturnedDate() {
		return expectedReturnedDate;
	}

	public void setExpectedReturnedDate(LocalDate expectedReturnedDate) {
		this.expectedReturnedDate = expectedReturnedDate;
	}

	public LocalDate getActualReturnedDate() {
		return actualReturnedDate;
	}

	public void setActualReturnedDate(LocalDate actualReturnedDate) {
		this.actualReturnedDate = actualReturnedDate;
	}

	public Integer getFine() {
		return fine;
	}

	public void setFine(Integer fine) {
		this.fine = fine;
	}

	public Boolean getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(Boolean isReturned) {
		this.isReturned = isReturned;
	}

}
