package com.iiht.training.elibrary.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.dto.StudentDto;
import com.iiht.training.elibrary.entity.Books;
import com.iiht.training.elibrary.entity.Student;
import com.iiht.training.elibrary.exception.StudentNotFoundException;
import com.iiht.training.elibrary.repository.BookIssueDetailsRepository;
import com.iiht.training.elibrary.repository.BooksRepository;
import com.iiht.training.elibrary.repository.StudentRepository;
import com.iiht.training.elibrary.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private BooksRepository booksRepository;

	@Override
	public StudentDto registerStudent(StudentDto studentDto) {
		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		repository.save(student);
		return studentDto;
	}

	@Override
	public List<BooksDto> getAllBooksByStudentStream(Long id) {
		StudentDto studentDto = findById(id);
		List<Books> books = booksRepository.findByStream(studentDto.getStream());
		List<BooksDto> booksDtos = new ArrayList<>();
		for (Books book : books) {
			BooksDto booksDto = new BooksDto();
			BeanUtils.copyProperties(book, booksDto);
			booksDtos.add(booksDto);
		}
		return booksDtos;
	}

	@Override
	public StudentDto findById(Long id) {
		Optional<Student> findById = repository.findById(id);
		if (findById.isPresent()) {
			StudentDto studentDto = new StudentDto();
			BeanUtils.copyProperties(findById.get(), studentDto);
			return studentDto;
		} else {
			throw new StudentNotFoundException("Student with id " + id + " does not exists");
		}
	}

}
