package com.iiht.training.elibrary.dto;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class BooksDto {

	private Long id;
	@NotBlank
	@Length(min = 3, max = 100)
	private String name;
	@NotBlank
	@Length(min = 13, max = 13)
	private String isbn;
	@NotBlank
	@Length(min = 3, max = 100)
	private String author;
	@NotBlank
	@Length(min = 3, max = 100)
	private String publisher;
	@NotNull
	@Min(2020)
	@Max(2030)
	private Integer publishedYear;
	@NotBlank
	@Length(min = 3, max = 100)
	private String edition;
	@NotBlank
	@Length(min = 3, max = 100)
	private String stream;
	@NotNull
	private Boolean issuedStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(Integer publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Boolean getIssuedStatus() {
		return issuedStatus;
	}

	public void setIssuedStatus(Boolean issuedStatus) {
		this.issuedStatus = issuedStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, edition, id, isbn, issuedStatus, name, publishedYear, publisher, stream);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooksDto other = (BooksDto) obj;
		return Objects.equals(author, other.author) && Objects.equals(edition, other.edition)
				&& Objects.equals(id, other.id) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(issuedStatus, other.issuedStatus) && Objects.equals(name, other.name)
				&& Objects.equals(publishedYear, other.publishedYear) && Objects.equals(publisher, other.publisher)
				&& Objects.equals(stream, other.stream);
	}

	
}
