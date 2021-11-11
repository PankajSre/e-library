package com.iiht.training.elibrary.functional;

import static com.iiht.training.elibrary.testutils.TestUtils.businessTestFile;
import static com.iiht.training.elibrary.testutils.TestUtils.currentTest;
import static com.iiht.training.elibrary.testutils.TestUtils.testReport;
import static com.iiht.training.elibrary.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.elibrary.controller.StudentController;
import com.iiht.training.elibrary.dto.BookIssueDetailsDto;
import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.dto.StudentDto;
import com.iiht.training.elibrary.service.BookIssueDetailsService;
import com.iiht.training.elibrary.service.BooksService;
import com.iiht.training.elibrary.service.StudentService;
import com.iiht.training.elibrary.testutils.MasterData;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
public class StudentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@MockBean
	private BooksService booksService;

	@MockBean
	private BookIssueDetailsService service;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterStudent() throws Exception {
		StudentDto studentDto = MasterData.getStudentDto();
		StudentDto savedStudentDto = MasterData.getStudentDto();

		savedStudentDto.setId(1L);

		when(this.studentService.registerStudent(studentDto)).thenReturn(savedStudentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students/register")
				.content(MasterData.asJsonString(studentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedStudentDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testRegisterStudentIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		StudentDto studentDto = MasterData.getStudentDto();
		StudentDto savedStudentDto = MasterData.getStudentDto();

		savedStudentDto.setId(1L);

		when(this.studentService.registerStudent(studentDto)).then(new Answer<StudentDto>() {

			@Override
			public StudentDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedStudentDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students/register")
				.content(MasterData.asJsonString(studentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}


	@Test
	public void testIssueBook() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		when(this.service.issueBook(1L, 1L)).thenReturn(detailsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/issue/1/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(detailsDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testIssueBookIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setId(1L);
		when(this.service.issueBook(1L, 1L)).then(new Answer<BookIssueDetailsDto>() {

			@Override
			public BookIssueDetailsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return detailsDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/issue/1/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllIssuedBooksByStudentId() throws Exception {
		List<BookIssueDetailsDto> detailsDtos = MasterData.getBookIssueDetailsDtoList();

		when(this.service.getAllIssuedBooksByStudentId(1L)).thenReturn(detailsDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/issued/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(detailsDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllIssuedBooksByStudentIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<BookIssueDetailsDto> detailsDtos = MasterData.getBookIssueDetailsDtoList();
		when(this.service.getAllIssuedBooksByStudentId(1L)).then(new Answer<List<BookIssueDetailsDto>>() {

			@Override
			public List<BookIssueDetailsDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return detailsDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/issued/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testReturnBook() throws Exception {
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		when(this.service.returnBook(1L)).thenReturn(detailsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/return/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(detailsDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testReturnBookIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BookIssueDetailsDto detailsDto = MasterData.getBookIssueDetailsDto();
		detailsDto.setId(1L);
		when(this.service.returnBook(1L)).then(new Answer<BookIssueDetailsDto>() {

			@Override
			public BookIssueDetailsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return detailsDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/return/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
