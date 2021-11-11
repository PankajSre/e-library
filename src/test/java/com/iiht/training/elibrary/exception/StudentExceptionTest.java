package com.iiht.training.elibrary.exception;

import static com.iiht.training.elibrary.testutils.TestUtils.currentTest;
import static com.iiht.training.elibrary.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.elibrary.testutils.TestUtils.testReport;
import static com.iiht.training.elibrary.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.elibrary.controller.StudentController;
import com.iiht.training.elibrary.dto.StudentDto;
import com.iiht.training.elibrary.service.BookIssueDetailsService;
import com.iiht.training.elibrary.service.BooksService;
import com.iiht.training.elibrary.service.StudentService;
import com.iiht.training.elibrary.testutils.MasterData;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
public class StudentExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;
	@MockBean
	private BooksService booksService;
	@MockBean
	private BookIssueDetailsService bookIssueDetailsService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterStudentInvalidStudentDetailsException() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		StudentDto savedStudentDto = MasterData.getStudentDto();

		savedStudentDto.setId(1L);
		studentDto.setName("Ab");

		when(this.studentService.registerStudent(studentDto)).thenReturn(savedStudentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students/register")
				.content(MasterData.asJsonString(studentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	
	@Test
	public void testGetAllBooksByStreamInvalidStreamException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("The Stream Maths does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.booksService.getAllBooksByStream("Maths"))
				.thenThrow(new InvalidStreamException("The Stream Maths does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/books/Maths")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	

	@Test
	public void testIssueBookStudentNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Student with id 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.bookIssueDetailsService.issueBook(1L, 1L))
				.thenThrow(new StudentNotFoundException("Student with id 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/issue/1/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
	
	@Test
	public void testIssueBookBookNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Book with id 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.bookIssueDetailsService.issueBook(1L, 1L))
				.thenThrow(new StudentNotFoundException("Book with id 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/issue/1/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
	@Test
	public void testIssueBookAlreadyIssuedException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("The Book with id 1 is already Issued",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.bookIssueDetailsService.issueBook(1L, 1L))
		.thenThrow(new StudentNotFoundException("The Book with id 1 is already Issued"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/issue/1/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
		
	}
	@Test
	public void testReturnBookIssueDetailsNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("The BookIssueDetails with id 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.bookIssueDetailsService.returnBook(1L))
		.thenThrow(new StudentNotFoundException("The BookIssueDetails with id 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/return/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
		
	}

	

}
