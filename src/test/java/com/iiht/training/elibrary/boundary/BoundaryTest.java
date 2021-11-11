package com.iiht.training.elibrary.boundary;

import static com.iiht.training.elibrary.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.elibrary.testutils.TestUtils.currentTest;
import static com.iiht.training.elibrary.testutils.TestUtils.testReport;
import static com.iiht.training.elibrary.testutils.TestUtils.yakshaAssert;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.training.elibrary.dto.BookIssueDetailsDto;
import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.dto.StudentDto;
import com.iiht.training.elibrary.testutils.MasterData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {
	private static Validator validator;

	// ----------------------------------------------------------------------------------------------
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testBookNameNotNull() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setName(null);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookNameMinThree() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setName("Ab");
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookNameMaxHundred() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		String name = "";
		for (int i = 0; i < 120; i++) {
			name.concat("A");
		}
		booksDto.setName(name);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIsbnNotNull() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setIsbn(null);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIsbnMinThirteen() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setIsbn("Ab");
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIsbnMaxThirteen() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		String isbn = "";
		for (int i = 0; i < 120; i++) {
			isbn.concat("A");
		}
		booksDto.setIsbn(isbn);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookAuthorNotNull() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setAuthor(null);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookAuthorMinThree() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setName("Ab");
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookAuthorMaxHunderd() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		String author = "";
		for (int i = 0; i < 120; i++) {
			author.concat("A");
		}
		booksDto.setAuthor(author);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookPublisherNotNull() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setPublisher(null);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookPublisherMinThree() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setPublisher("Ab");
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookPublisherMaxHunderd() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		String publisher = "";
		for (int i = 0; i < 120; i++) {
			publisher.concat("A");
		}
		booksDto.setPublisher(publisher);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookPublishedYearNotNull() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setPublishedYear(null);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookPublishedYearMin2020() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setPublishedYear(2010);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookPublishedYearMax2030() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setPublishedYear(2040);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookEditionNotNull() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setEdition(null);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookEditionMinThree() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setEdition("Ab");
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookEditionMaxHunderd() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		String edition = "";
		for (int i = 0; i < 120; i++) {
			edition.concat("A");
		}
		booksDto.setEdition(edition);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookStreamNotNull() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setStream(null);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookStreamMinThree() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setStream("Ab");
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookStreamMaxHunderd() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		String stream = "";
		for (int i = 0; i < 120; i++) {
			stream.concat("A");
		}
		booksDto.setStream(stream);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueStatusNotNull() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		booksDto.setIssuedStatus(null);
		Set<ConstraintViolation<BooksDto>> violations = validator.validate(booksDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentNameNotNull() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setName(null);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentNameMinThree() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setName("Ab");
		;
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentNameMaxHunderd() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		String name = "";
		for (int i = 0; i < 120; i++) {
			name.concat("A");
		}
		studentDto.setName(name);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentEmailNotNull() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setEmail(null);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentEmailMinThree() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setEmail("Ab");
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentEmailMaxHunderd() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		String email = "";
		for (int i = 0; i < 120; i++) {
			email.concat("A");
		}
		studentDto.setEmail(email);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentEmailValidFormat() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setEmail("abc");
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentStreamNotNull() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setStream(null);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentStreamMinThree() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setStream("Ab");
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentStreamMaxHunderd() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		String stream = "";
		for (int i = 0; i < 120; i++) {
			stream.concat("A");
		}
		studentDto.setStream(stream);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentPhoneNumberNotNull() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setPhoneNumber(null);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentPhoneNumberMinTen() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setPhoneNumber(123456L);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentPhoneNumberMaxTen() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setPhoneNumber(123456789012L);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentDateOfBirthNotNull() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setDateOfBirth(null);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentDateOfBirthNotFutureDate() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setDateOfBirth(LocalDate.of(2023, 10, 10));
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentAddressNotNull() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setAddress(null);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentaddressMinThree() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		studentDto.setAddress("Ab");
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStudentAddressMaxHunderd() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		String address = "";
		for (int i = 0; i < 120; i++) {
			address.concat("A");
		}
		studentDto.setAddress(address);
		Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsStudentIdNotNull() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setStudentId(null);
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsBookIdNotNull() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setBookId(null);
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsIssueDateNotNull() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setIssueDate(null);
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsExpectedReturnedDateNotNull() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setExpectedReturnedDate(null);
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsExpectedReturnedDateNotPastDate() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setExpectedReturnedDate(LocalDate.of(2020, 10, 10));
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsActualReturnedDateNotNull() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setActualReturnedDate(null);
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsActualReturnedDateNotPastDate() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setActualReturnedDate(LocalDate.of(2020, 10, 10));
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsFineNotNull() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setFine(null);
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	@Test
	public void testBookIssueDetailsFineMinZero() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setFine(-10);
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBookIssueDetailsIsReturnedNotNull() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setIsReturned(null);
		Set<ConstraintViolation<BookIssueDetailsDto>> violations = validator.validate(detailsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
}
