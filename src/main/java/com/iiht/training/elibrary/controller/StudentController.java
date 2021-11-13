package com.iiht.training.elibrary.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.training.elibrary.dto.BookIssueDetailsDto;
import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.dto.StudentDto;
import com.iiht.training.elibrary.exception.InvalidStreamException;
import com.iiht.training.elibrary.exception.InvalidStudentDetailsException;
import com.iiht.training.elibrary.service.BookIssueDetailsService;
import com.iiht.training.elibrary.service.BooksService;
import com.iiht.training.elibrary.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private BookIssueDetailsService service;

	@Autowired
	private BooksService booksService;

	@PostMapping("/register")
	public ResponseEntity<StudentDto> registerStudent(@Valid @RequestBody StudentDto studentDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidStudentDetailsException("The Student details are not valid");
		}
		studentService.registerStudent(studentDto);
		return ResponseEntity.ok(studentDto);
	}

	@GetMapping("/books/{stream}")
	public ResponseEntity<List<BooksDto>> getAllBooksByStream(@PathVariable String stream) {
		List<String> streams = Arrays.asList("Science", "Commerce", "Arts", "Management", "Media");
		if (streams.contains(stream)) {
			List<BooksDto> list = booksService.getAllBooksByStream(stream);
			return new ResponseEntity<List<BooksDto>>(list, HttpStatus.OK);
		} else {
			throw new InvalidStreamException("The Stream " + stream + " does not exists");
		}

	}

	@GetMapping("/issue/{studentId}/{bookId}")
	public ResponseEntity<BookIssueDetailsDto> issueBook(@PathVariable Long studentId, @PathVariable Long bookId) {
		BookIssueDetailsDto issueBook = service.issueBook(studentId, bookId);
		return ResponseEntity.ok(issueBook);

	}

	@GetMapping("/issued/{id}")
	public ResponseEntity<List<BookIssueDetailsDto>> getAllIssuedBooksByStudentId(@PathVariable Long id) {
		List<BookIssueDetailsDto> allIssuedBooks = service.getAllIssuedBooksByStudentId(id);
		return new ResponseEntity<List<BookIssueDetailsDto>>(allIssuedBooks, HttpStatus.FOUND);

	}

	@GetMapping("/return/{id}")
	public ResponseEntity<BookIssueDetailsDto> returnBook(@PathVariable Long id) {
		BookIssueDetailsDto returnBook = service.returnBook(id);
		return new ResponseEntity<BookIssueDetailsDto>(returnBook, HttpStatus.OK);

	}

	@GetMapping("/books/by/{id}")
	public ResponseEntity<List<BooksDto>> getAllBooksByStudentStream(@PathVariable Long id) {
		List<BooksDto> booksByStudentStream = studentService.getAllBooksByStudentStream(id);

		return new ResponseEntity<List<BooksDto>>(booksByStudentStream, HttpStatus.OK);

	}
}
