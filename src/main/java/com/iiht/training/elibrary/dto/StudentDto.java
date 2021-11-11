package com.iiht.training.elibrary.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentDto {

	private Long id;
	@NotBlank
	@Length(min = 3, max = 100)
	private String name;
	@NotBlank
	@Length(min = 3, max = 100)
	@Email
	private String email;
	@NotBlank
	@Length(min = 3, max = 100)
	private String stream;
	@NotNull
	@Min(1000000000)
	@Max(9999999999L)
	private Long phoneNumber;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate dateOfBirth;
	@NotBlank
	@Length(min = 3, max = 100)
	private String address;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, dateOfBirth, email, id, name, phoneNumber, stream);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(stream, other.stream);
	}

	
}
