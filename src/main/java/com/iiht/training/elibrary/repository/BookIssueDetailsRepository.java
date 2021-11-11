package com.iiht.training.elibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.elibrary.entity.BookIssueDetails;

@Repository
public interface BookIssueDetailsRepository extends JpaRepository<BookIssueDetails, Long> {

	List<BookIssueDetails> findByStudentId(Long studentId);
}
