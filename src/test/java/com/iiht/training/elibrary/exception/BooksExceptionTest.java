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

import com.iiht.training.elibrary.controller.BooksController;
import com.iiht.training.elibrary.dto.BooksDto;
import com.iiht.training.elibrary.service.BooksService;
import com.iiht.training.elibrary.testutils.MasterData;

@WebMvcTest(BooksController.class)
@AutoConfigureMockMvc
public class BooksExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BooksService booksService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testAddBookInvalidBookDetailsException() throws Exception {
		BooksDto booksDto = MasterData.getBooksDto();
		BooksDto savedBooksDto = MasterData.getBooksDto();

		savedBooksDto.setId(1L);
		booksDto.setName("Ab");

		when(this.booksService.saveBooks(booksDto)).thenReturn(savedBooksDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books/add")
				.content(MasterData.asJsonString(booksDto)).contentType(MediaType.APPLICATION_JSON)
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
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/Maths")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

}
