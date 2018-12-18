package com.stockmgt.errors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Abderrahmen ISSA
 */
@Getter
@Setter
@NoArgsConstructor
public class RestError {

	private String error;
	private String message;
	private Integer status;

}
