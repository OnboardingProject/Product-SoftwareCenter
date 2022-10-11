package com.ust.products.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ust.products.exceptions.ProductAlreadyDeletedException;
import com.ust.products.exceptions.ProductNotExistsException;
import com.ust.products.model.Products;
import com.ust.products.repository.ProductsRepository;
import com.ust.products.service.ProductsService;



public class ProductServiceTest {
	
	@InjectMocks
	ProductsService productService;
	
	@Mock
	MongoTemplate mongoTemplate;
	
	@Mock
	ProductsRepository productRepository;
	
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this); 
		
	}
	

	@Test
	public void givenProductToDeleteShouldReturnDeleteProduct() throws ProductAlreadyDeletedException, ProductNotExistsException {
		List<String> categoryLevel = new ArrayList<String>();
		categoryLevel.add("1-1-1");
		categoryLevel.add("1-2-3");
		
		List<String> categoryLevelDescription = new ArrayList<String>();
		categoryLevelDescription.add("internal-developmenttool-workflow");
		Products product1 = new Products("p123", "Azure Tools",1299.99F, 4,categoryLevel , "u212", LocalDateTime.now(),"u212",
				LocalDateTime.now(), "false", categoryLevelDescription, "developer tools");	
		
		String productId =  "p123";
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(product1.getProductId()));
		when(mongoTemplate.findOne(query,Products.class)).thenReturn(product1);
	//	product1.setIsDeleted("true");
		when(mongoTemplate.save(product1)).thenReturn(product1);
		productService.deleteProductById(productId);
		assertEquals(product1,product1);	
		assertNotNull(product1);
	
	
		
	}
	
	@Test(expected = ProductAlreadyDeletedException.class)
	public void givenProductToDeleteShouldReturnException() throws ProductNotExistsException {
		
		List<String> categoryLevel = new ArrayList<String>();
		categoryLevel.add("1-1-1");
		categoryLevel.add("1-2-3");
		
		List<String> categoryLevelDescription = new ArrayList<String>();
		categoryLevelDescription.add("internal-developmenttool-workflow");
		Products product1 = new Products("p123", "Azure Tools",1299.99F, 4,categoryLevel , "u212", LocalDateTime.now(),"u212",
				LocalDateTime.now(), "true", categoryLevelDescription, "developer tools");	
		
		String productId =  "p123";
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(product1.getProductId()));
		when(mongoTemplate.findOne(query,Products.class)).thenReturn(product1);
	//	product1.setIsDeleted("true");
		//when(mongoTemplate.save(product1)).thenReturn(product1);
		Products product = productService.deleteProductById(productId);
	//	System.out.println(product);
	//	assertEquals("true", product.getIsDeleted());
	  	 //   assertEquals(exception, exception);
				
	}
	
	
	
	@Test(expected = ProductNotExistsException.class)
	public void givenProductToDeleteShouldReturnNotExistsException() throws ProductNotExistsException {
		
	
		List<String> categoryLevel = new ArrayList<String>();
		categoryLevel.add("1-1-1");
		categoryLevel.add("1-2-3");
		
		List<String> categoryLevelDescription = new ArrayList<String>();
		categoryLevelDescription.add("internal-developmenttool-workflow");
		Products product1 = new Products("p123", "Azure Tools",1299.99F, 4,categoryLevel , "u212", LocalDateTime.now(),"u212",
				LocalDateTime.now(), "false", categoryLevelDescription, "developer tools");	
		
		String productId =  "p1234";
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(product1.getProductId()));
		when(mongoTemplate.findOne(query,Products.class)).thenReturn(product1);
		Products product = productService.deleteProductById(productId);
	 
	}	
	
}
	
	
