package com.iiht.training.elibrary.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.entity.BookIssueDetails;
import com.iiht.training.elibrary.entity.Books;
import com.iiht.training.elibrary.repository.BookIssueDetailsRepository;
import com.iiht.training.elibrary.repository.BooksRepository;
import com.iiht.training.elibrary.service.BooksService;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private BookIssueDetailsRepository repository;

	@Override
	public BooksDto saveBooks(BooksDto booksDto) {
		Books books = new Books();
		BeanUtils.copyProperties(booksDto, books);
		booksRepository.save(books);
		return booksDto;
	}

	@Override
	public List<BooksDto> getAllIssuedBooks() {
		List<Books> allBooks = booksRepository.findAll();
		List<BooksDto> issuedBooks = new ArrayList<>();
		for (Books book : allBooks) {
			BooksDto dto = new BooksDto();
			if (book.getIssuedStatus() == true) {
				BeanUtils.copyProperties(book, dto);
				issuedBooks.add(dto);
			}
		}
		return issuedBooks;
	}

	@Override
	public BooksDto updateBook(BooksDto booksDto) {
		Books books = new Books();
		BeanUtils.copyProperties(booksDto, books);
		booksRepository.save(books);
		return booksDto;
	}

	@Override
	public List<BooksDto> getAllBooksByStream(String stream) {
		List<Books> books = booksRepository.findByStream(stream);
		List<BooksDto> booksDtos = new ArrayList<>();
		for (Books book : books) {
			BooksDto booksDto = new BooksDto();
			BeanUtils.copyProperties(book, booksDto);
			booksDtos.add(booksDto);
		}
		return booksDtos;
	}

	@Override
	public List<BooksDto> getAllIssuedBooksWithFine() {
		List<BookIssueDetails> list = repository.findAll();
		List<BooksDto> dtos = new ArrayList<>();
		for (BookIssueDetails details : list) {
			if (details.getFine() > 0) {
				Optional<Books> findById = booksRepository.findById(details.getBookId());
				BooksDto booksDto = new BooksDto();
				BeanUtils.copyProperties(findById.get(), booksDto);
				dtos.add(booksDto);
			}
		}
		return dtos;
	}

}
