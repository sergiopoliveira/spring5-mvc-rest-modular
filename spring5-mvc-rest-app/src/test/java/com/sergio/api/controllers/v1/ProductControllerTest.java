package com.sergio.api.controllers.v1;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sergio.api.v1.model.ProductDTO;
import com.sergio.controllers.v1.ProductController;
import com.sergio.controllers.v1.RestResponseEntityExceptionHandler;
import com.sergio.service.ProductService;

public class ProductControllerTest {

	private static final Long ID = 1L;
	private static final String NAME = "Pineapple";
	
	@Mock
	ProductService productService;
	
	@InjectMocks
	ProductController productController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(productController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())
				.build();
	}
	
	@Test
	public void testListProducts() throws Exception {
		ProductDTO product1 = new ProductDTO();
		product1.setName(NAME);
		
		ProductDTO product2 = new ProductDTO();
		product2.setName("Factory Inc");
		
		List<ProductDTO> products = Arrays.asList(product1, product2);
		
		when(productService.getAllProducts()).thenReturn(products);
		
		mockMvc.perform(get(ProductController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.products", hasSize(2)));
	}
}
