package com.ust.products.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ust.products.model.Products;



@Repository
public interface ProductsRepository extends MongoRepository<Products, String>{

	List<Products> findByProductName(String productName);

}
