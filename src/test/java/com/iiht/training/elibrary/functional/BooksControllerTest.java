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

import com.iiht.training.elibrary.controller.BooksController;
import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.service.BooksService;
import com.iiht.training.elibrary.testutils.MasterData;

@WebMvcTest(BooksController.class)
@AutoConfigureMockMvc
public class BooksControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BooksService booksService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testSaveBook() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		BooksDto savedBooksDto = MasterData.getBooksDto();
		savedBooksDto.setId(1L);

		when(this.booksService.saveBooks(booksDto)).thenReturn(savedBooksDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books/add")
				.content(MasterData.asJsonString(booksDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedBooksDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSaveBookIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BooksDto booksDto = MasterData.getBooksDto();
		BooksDto savedBooksDto = MasterData.getBooksDto();
		savedBooksDto.setId(1L);

		when(this.booksService.saveBooks(booksDto)).then(new Answer<BooksDto>() {

			@Override
			public BooksDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedBooksDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books/add")
				.content(MasterData.asJsonString(booksDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllIssuedBooks() throws Exception {
		List<BooksDto> bookDtos = MasterData.getBooksDtoList();

		when(this.booksService.getAllIssuedBooks()).thenReturn(bookDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/issued")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bookDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllIssuedBooksIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<BooksDto> bookDtos = MasterData.getBooksDtoList();
		when(this.booksService.getAllIssuedBooks()).then(new Answer<List<BooksDto>>() {

			@Override
			public List<BooksDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return bookDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/issued")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	public void testGetAllBooksByStream() throws Exception {
		List<BooksDto> bookDtos = MasterData.getBooksDtoList();

		when(this.booksService.getAllBooksByStream("Science")).thenReturn(bookDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/Science")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bookDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllBooksByStreamIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<BooksDto> bookDtos = MasterData.getBooksDtoList();
		when(this.booksService.getAllBooksByStream("Science")).then(new Answer<List<BooksDto>>() {

			@Override
			public List<BooksDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return bookDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/Science")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testgetAllIssuedBooksWithFine() throws Exception {
		List<BooksDto> bookDtos = MasterData.getBooksDtoList();

		when(this.booksService.getAllIssuedBooksWithFine()).thenReturn(bookDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/fined")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bookDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testgetAllIssuedBooksWithFineIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<BooksDto> bookDtos = MasterData.getBooksDtoList();
		when(this.booksService.getAllIssuedBooksWithFine()).then(new Answer<List<BooksDto>>() {

			@Override
			public List<BooksDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return bookDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/fined")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
