package com.ust.products.exceptions;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorInfo {
	
	private String errorMessage;
	private LocalDateTime dateTime;

}
