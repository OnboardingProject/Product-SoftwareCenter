package com.ust.products.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ust.products.controller.ProductsController;
import com.ust.products.model.Products;
import com.ust.products.service.ProductsService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	
	@Mock
	ProductsService productService;
	
	@InjectMocks
	ProductsController controller;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
    public void givenProductToDeleteShouldReturnDeleteProduct() throws Exception{
		
		//Product product = new Product(); 
		String productId = "p123";
		List<String> categoryLevel = new ArrayList<String>();
		categoryLevel.add("1-1-1");
		categoryLevel.add("1-2-3");
		
		List<String> categoryLevelDescription = new ArrayList<String>();
		categoryLevelDescription.add("internal-developmenttool-workflow");
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		Products product1 = new Products("p123", "Azure Tools",1299.99F, 4,categoryLevel , "u212",dateTime,"u212",
				dateTime, "false", categoryLevelDescription, "developer tools");
		
		when(productService.deleteProductById(productId)).thenReturn(product1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/com/ust/product/p123")
				.contentType(MediaType.APPLICATION_JSON))
			//	.content(new ObjectMapper().writeValueAsString(product1)))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	//verify(productService,timeout(2)).deleteProductById(productId);
		
	}


	@Test
	public void givenProductToDeleteShouldReturnStatus() throws Exception {
		String productId = "p1234";
		List<String> categoryLevel = new ArrayList<String>();
		categoryLevel.add("1-1-1");
		categoryLevel.add("1-2-3");
		
		List<String> categoryLevelDescription = new ArrayList<String>();
		categoryLevelDescription.add("internal-developmenttool-workflow");
		Products product1 = new Products("p123", "Azure Tools",1299.99F, 4,categoryLevel , "u212", LocalDateTime.now(),"u212",
				LocalDateTime.now(), "true", categoryLevelDescription, "developer tools");
		
		when(productService.deleteProductById(productId)).thenReturn(product1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/com/ust/product/p123") 
				.contentType(MediaType.APPLICATION_JSON))
		      // .content(new ObjectMapper().writeValueAsString(product1)))
		        .andExpect(MockMvcResultMatchers.status().isNotFound());
		        
	//	verify(productService,timeout(1)).deleteProductById(productId);
		
	}
}	
	
   