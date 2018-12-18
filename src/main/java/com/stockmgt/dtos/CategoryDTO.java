package com.stockmgt.dtos;

import lombok.Getter;
import lombok.Setter;

// Generated 10 d�c. 2018 14:48:45 by Hibernate Tools 5.3.6.Final

/**
 * Data Transfert Object to @Category
 * 
 * @author Abderrahmen ISSA
 * 
 */
@Getter
@Setter
public class CategoryDTO extends BasicDTO {

	private String categoryName;

	@Override
	public String toString() {
		return "Category [" + categoryName + "]";
	}

}
