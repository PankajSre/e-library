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

import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.exception.InvalidBookDetailsException;
import com.iiht.training.elibrary.exception.InvalidStreamException;
import com.iiht.training.elibrary.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	private BooksService booksService;

	@PostMapping("/add")
	public ResponseEntity<BooksDto> saveBook(@Valid @RequestBody BooksDto booksDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidBookDetailsException("The Book details entered are not valid");
		}
		booksService.saveBooks(booksDto);
		return ResponseEntity.ok(booksDto);
	}

	@GetMapping("/issued")
	public ResponseEntity<List<BooksDto>> getAllIssuedBooks() {
		List<BooksDto> allIssuedBooks = booksService.getAllIssuedBooks();
		return ResponseEntity.ok(allIssuedBooks);
	}

	@GetMapping("/{stream}")
	public ResponseEntity<List<BooksDto>> getAllBooksByStream(@PathVariable String stream) {
		List<String> streams = Arrays.asList("Science", "Commerce", "Arts", "Management", "Media");
		if (streams.contains(stream)) {
			List<BooksDto> list = booksService.getAllBooksByStream(stream);
			return new ResponseEntity<List<BooksDto>>(list, HttpStatus.FOUND);
		} else {
			throw new InvalidStreamException("The Stream " + stream + " does not exists");
		}

	}

	@GetMapping("/fined")
	public ResponseEntity<List<BooksDto>> getAllIssuedBooksWithFine() {
		List<BooksDto> allIssuedBooks = booksService.getAllIssuedBooksWithFine();
		return ResponseEntity.ok(allIssuedBooks);
	}
}
