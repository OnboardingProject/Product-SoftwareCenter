package com.ust.products.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.products.constants.ProductsConstants;
import com.ust.products.exceptions.ProductNotExistsException;
import com.ust.products.model.Products;
import com.ust.products.service.ProductsService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * This is the controller class of Product where we create the 
 * add,delete,search and update product end points
 * @author 226738
 * 
 */

@Slf4j
@RestController
@RequestMapping("/com/ust/product")
public class ProductsController {
	
	@Autowired
	ProductsService productService;
	
	
	

	/**
	 * 
	 * @param productId
	 * @return status of deleteProduct
	 * @throws ProductNotExistsException 
	 * @throws Exception
	 */
	@DeleteMapping("/{productId}")
	@Operation(summary ="delete product softly",description = "This Api is used to delete products temporarly")
	public ResponseEntity<?> deleteProduct(@PathVariable ("productId") String productId) throws ProductNotExistsException {
		log.info("Controller soft delete product starts with productRequest:{}", productId);
		Products product = productService.deleteProductById(productId);
		if(ObjectUtils.isNotEmpty(product)) {
		log.info("controller softDeleteProduct method ends with http status OK response:{}",product);
		return new ResponseEntity<>(ProductsConstants.PRODUCT_DELETED,HttpStatus.OK);
		}else
			return new ResponseEntity<>(ProductsConstants.PRODUCT_NOT_EXISTS,HttpStatus.NOT_FOUND);
	}
	
}	
	
