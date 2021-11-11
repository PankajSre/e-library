package com.iiht.training.elibrary.service;

import java.util.List;

import com.iiht.training.elibrary.dto.BookIssueDetailsDto;

public interface BookIssueDetailsService {

	public BookIssueDetailsDto issueBook(Long studentId, Long bookId);

	public List<BookIssueDetailsDto> getAllIssuedBooksByStudentId(Long studentId);

	public BookIssueDetailsDto returnBook(Long id);
}
