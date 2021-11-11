package com.iiht.training.elibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.elibrary.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

	List<Books> findByStream(String stream);
}
