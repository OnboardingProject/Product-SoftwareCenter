package com.ust.products.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.ust.products.constants.ProductsConstants;
import com.ust.products.exceptions.ProductAlreadyDeletedException;
import com.ust.products.exceptions.ProductException;
import com.ust.products.exceptions.ProductNotExistsException;
import com.ust.products.model.Products;
import com.ust.products.repository.ProductsRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProductsService {
	

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
    ProductsRepository productRepository;




	/**
	 * 
	 * @param productId
	 * @return message of delete product
	 * @throws ProductNotExistsException 
	 * @throws ProductException
	 * 
	 */

	public Products deleteProductById(String productId) throws ProductNotExistsException {
		
	 //   try {
            log.info("deleteProduct service starts with request : {}", productId);
            Query query = new Query();
            query.addCriteria(Criteria.where("productId").is(productId));
            Products existingProduct = mongoTemplate.findOne(query, Products.class);
            // mongoTemplate.findById(productId, Product.class);
           log.info("Product fetched from DB : {}", existingProduct);
            if (!ObjectUtils.isEmpty(existingProduct) && existingProduct.getIsDeleted().equalsIgnoreCase("false")) {
                log.info("service deleteProduct exists and the given deleted status is false");
                existingProduct.setIsDeleted("true");
                return mongoTemplate.save(existingProduct);
            } else if (!ObjectUtils.isEmpty(existingProduct) && existingProduct.getIsDeleted().equalsIgnoreCase("true"))  {
                log.error("Service deleteProduct is already deleted");
                throw new ProductAlreadyDeletedException(ProductsConstants.PRODUCT_ALREADY_DELETED);
            }else {
     //   } catch (ProductException ex) {
            log.error("Service deleteProduct is not found");
            throw new ProductNotExistsException(ProductsConstants.PRODUCT_NOT_EXISTS);

       }
    }
}	
	