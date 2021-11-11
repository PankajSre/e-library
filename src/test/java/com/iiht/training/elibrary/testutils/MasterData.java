package com.iiht.training.elibrary.testutils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iiht.training.elibrary.dto.BookIssueDetailsDto;
import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.dto.StudentDto;

public class MasterData {

	public static BooksDto getBooksDto() {
		BooksDto booksDto = new BooksDto();
		booksDto.setId(1L);
		booksDto.setName("Programming in Java");
		booksDto.setIsbn("3212345674563");
		booksDto.setAuthor("Anmol Kumar");
		booksDto.setPublisher("BPB Publications");
		booksDto.setEdition("7th Edition");
		booksDto.setIssuedStatus(false);
		booksDto.setPublishedYear(2022);
		booksDto.setStream("Science");
		return booksDto;
	}

	public static List<BooksDto> getBooksDtoList() {
		List<BooksDto> booksDtos = new ArrayList<>();
		BooksDto booksDto = new BooksDto();
		booksDto.setId(1L);
		booksDto.setName("Programming in Java");
		booksDto.setIsbn("3212345674563");
		booksDto.setAuthor("Anmol Kumar");
		booksDto.setPublisher("BPB Publications");
		booksDto.setEdition("7th Edition");
		booksDto.setIssuedStatus(false);
		booksDto.setPublishedYear(2022);
		booksDto.setStream("Science");
		booksDtos.add(booksDto);
		booksDto = new BooksDto();
		booksDto.setId(2L);
		booksDto.setName("Art of Living");
		booksDto.setIsbn("9000345674563");
		booksDto.setAuthor("Madhu Sharma");
		booksDto.setPublisher("Public Publications");
		booksDto.setEdition("3th Edition");
		booksDto.setIssuedStatus(false);
		booksDto.setPublishedYear(2023);
		booksDto.setStream("Arts");
		booksDtos.add(booksDto);
		return booksDtos;

	}

	public static StudentDto getStudentDto() {
		StudentDto dto = new StudentDto();
		dto.setId(1L);
		dto.setEmail("user@gmail.com");
		dto.setName("User");
		dto.setDateOfBirth(LocalDate.of(2010, 8, 21));
		dto.setAddress("New Delhi");
		dto.setPhoneNumber(8712098909L);
		dto.setStream("Commerce");
		return dto;
	}

	public static List<StudentDto> getStudentDtoList() {
		List<StudentDto> dtos = new ArrayList<>();
		StudentDto dto = new StudentDto();
		dto.setId(1L);
		dto.setEmail("user@gmail.com");
		dto.setName("User");
		dto.setDateOfBirth(LocalDate.of(2010, 8, 21));
		dto.setAddress("New Delhi");
		dto.setPhoneNumber(8712098909L);
		dto.setStream("Commerce");
		dtos.add(dto);
		dto = new StudentDto();
		dto.setId(2L);
		dto.setEmail("admin@gmail.com");
		dto.setName("Admin");
		dto.setDateOfBirth(LocalDate.of(2011, 8, 10));
		dto.setAddress("Bangalore");
		dto.setPhoneNumber(9090908888L);
		dto.setStream("Science");
		dtos.add(dto);
		return dtos;
	}

	public static BookIssueDetailsDto getBookIssueDetailsDto() {
		BookIssueDetailsDto dto = new BookIssueDetailsDto();
		dto.setId(1L);
		dto.setBookId(1L);
		dto.setStudentId(1L);
		dto.setIssueDate(LocalDate.now());
		dto.setExpectedReturnedDate(LocalDate.now().plus(1, ChronoUnit.WEEKS));
		dto.setExpectedReturnedDate(LocalDate.now().plus(1, ChronoUnit.WEEKS));
		dto.setFine(0);
		dto.setIsReturned(true);
		return dto;
	}

	public static List<BookIssueDetailsDto> getBookIssueDetailsDtoList() {
		List<BookIssueDetailsDto> dtos = new ArrayList<>();
		BookIssueDetailsDto dto = new BookIssueDetailsDto();
		dto.setId(1L);
		dto.setBookId(1L);
		dto.setStudentId(1L);
		dto.setIssueDate(LocalDate.now());
		dto.setExpectedReturnedDate(LocalDate.now().plus(1, ChronoUnit.WEEKS));
		dto.setExpectedReturnedDate(LocalDate.now().plus(1, ChronoUnit.WEEKS));
		dto.setFine(0);
		dto.setIsReturned(true);
		dtos.add(dto);
		dto = new BookIssueDetailsDto();
		dto.setId(2L);
		dto.setBookId(2L);
		dto.setStudentId(2L);
		dto.setIssueDate(LocalDate.now());
		dto.setExpectedReturnedDate(LocalDate.now().plus(1, ChronoUnit.WEEKS));
		dto.setExpectedReturnedDate(LocalDate.now().plus(2, ChronoUnit.WEEKS));
		dto.setFine(35);
		dto.setIsReturned(true);
		dtos.add(dto);
		return dtos;

	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
