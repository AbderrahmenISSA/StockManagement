package com.stockmgt.controllers.v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestError {

	private String error;
	private String error_description;
	private Integer error_code;

}
