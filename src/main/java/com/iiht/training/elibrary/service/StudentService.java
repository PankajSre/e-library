package com.iiht.training.elibrary.service;

import java.util.List;

import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.dto.StudentDto;

public interface StudentService {

	public StudentDto registerStudent(StudentDto studentDto);
	
	public List<BooksDto> getAllBooksByStream(String stream);
	
	
}
