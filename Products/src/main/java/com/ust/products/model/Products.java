package com.ust.products.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


	
	@Document
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class Products {

		@Id
		private String productId;
		
		@NotNull(message = "Product name should not be null")
		private String productName;
		
		@NotNull(message = "This fiels is empty")
		private float  contractSpend;
		
		@NotNull(message = "Please provide the value")
		private int stakeholder;
		
		@NotEmpty(message = "This field shouls not be empty")
		private List<String> categoryLevel;
		
		@NotEmpty(message = "Please fill this field")
		private String createdBy;
		
		private LocalDateTime createdTime;
		
		@NotNull
		private String lastUpdatedBy;
		
		private LocalDateTime lastUpdatedTime;
		
		@NotBlank
		private String isDeleted;
		
		private List<String> categoryLevelDescription;
		
		private String productDesscription;
		
		
		
		
	

}
