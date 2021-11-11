package com.iiht.training.elibrary.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.elibrary.dto.BookIssueDetailsDto;
import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.entity.BookIssueDetails;
import com.iiht.training.elibrary.entity.Books;
import com.iiht.training.elibrary.entity.Student;
import com.iiht.training.elibrary.exception.AlreadyIssuedException;
import com.iiht.training.elibrary.exception.BookIssueDetailsNotFoundException;
import com.iiht.training.elibrary.exception.BookNotFoundException;
import com.iiht.training.elibrary.exception.StudentNotFoundException;
import com.iiht.training.elibrary.repository.BookIssueDetailsRepository;
import com.iiht.training.elibrary.repository.BooksRepository;
import com.iiht.training.elibrary.repository.StudentRepository;
import com.iiht.training.elibrary.service.BookIssueDetailsService;
import com.iiht.training.elibrary.service.BooksService;

@Service
public class BookIssueDetailsServiceImpl implements BookIssueDetailsService {

	@Autowired
	private BookIssueDetailsRepository repository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private BooksService booksService;

	@Override
	public BookIssueDetailsDto issueBook(Long studentId, Long bookId) {
		Optional<Student> student = studentRepository.findById(studentId);
		Optional<Books> book = booksRepository.findById(bookId);
		if (!student.isPresent()) {
			throw new StudentNotFoundException("Student with id " + studentId + " does not exists");
		}
		if (!book.isPresent()) {
			throw new BookNotFoundException("Book with id " + bookId + " does not exists");
		}
		Optional<Books> findById = booksRepository.findById(bookId);

		BooksDto booksDto = new BooksDto();
		BeanUtils.copyProperties(findById.get(), booksDto);
		if (booksDto.getIssuedStatus() == false) {
			throw new AlreadyIssuedException("The Book with id " + bookId + " is already Issued");
		}
		booksDto.setIssuedStatus(true);
		booksService.updateBook(booksDto);

		BookIssueDetailsDto dto = new BookIssueDetailsDto();
		dto.setStudentId(studentId);
		dto.setBookId(bookId);
		dto.setIssueDate(LocalDate.now());
		dto.setExpectedReturnedDate(LocalDate.now().plus(1, ChronoUnit.WEEKS));
		dto.setFine(0);
		dto.setActualReturnedDate(null);
		dto.setIsReturned(false);
		BookIssueDetails details = new BookIssueDetails();
		BeanUtils.copyProperties(dto, details);
		repository.save(details);

		return dto;
	}

	@Override
	public List<BookIssueDetailsDto> getAllIssuedBooksByStudentId(Long studentId) {
		Optional<Student> findById = studentRepository.findById(studentId);
		if (findById.isPresent()) {
			List<BookIssueDetails> bookIssued = repository.findByStudentId(studentId);
			List<BookIssueDetailsDto> dtos = new ArrayList<>();
			for (BookIssueDetails issueDetails : bookIssued) {
				BookIssueDetailsDto dto = new BookIssueDetailsDto();
				BeanUtils.copyProperties(issueDetails, dto);
				dtos.add(dto);
			}
			return dtos;
		} else {
			throw new StudentNotFoundException("The Student with id " + studentId + " does not exists");
		}

	}

	@Override
	public BookIssueDetailsDto returnBook(Long id) {
		Optional<BookIssueDetails> bookDetails = repository.findById(id);
		if (!bookDetails.isPresent()) {
			throw new BookIssueDetailsNotFoundException("The BookIssueDetails with id " + id + " does not exists");
		}
		BookIssueDetailsDto dto = new BookIssueDetailsDto();
		BeanUtils.copyProperties(bookDetails.get(), dto);
		LocalDate expected = dto.getExpectedReturnedDate();
		LocalDate today = LocalDate.now();
		dto.setActualReturnedDate(today);
		dto.setIsReturned(true);
		if (today.isBefore(expected) || today.isEqual(expected)) {
			dto.setFine(0);
		} else {
			Period period = Period.between(expected, today);
			int days = period.getDays();
			int fine = days * 5;
			dto.setFine(fine);
		}
		BookIssueDetails details = new BookIssueDetails();
		BeanUtils.copyProperties(dto, details);
		repository.save(details);
		Optional<Books> findById = booksRepository.findById(dto.getBookId());
		BooksDto booksDto = new BooksDto();
		BeanUtils.copyProperties(findById.get(), booksDto);
		booksDto.setIssuedStatus(false);
		booksService.updateBook(booksDto);
		return dto;
	}

}
