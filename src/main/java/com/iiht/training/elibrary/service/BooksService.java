package com.iiht.training.elibrary.service;

import java.util.List;

import com.iiht.training.elibrary.dto.BooksDto;

public interface BooksService {

	public BooksDto saveBooks(BooksDto booksDto);
	public List<BooksDto> getAllIssuedBooks();
	public BooksDto updateBook(BooksDto booksDto);
	public List<BooksDto> getAllBooksByStream(String stream);
	public List<BooksDto> getAllIssuedBooksWithFine();
}
