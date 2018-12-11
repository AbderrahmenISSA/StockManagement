package com.stockmgt.dtos;
// Generated 10 d�c. 2018 14:48:45 by Hibernate Tools 5.3.6.Final

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfert Object to @Product
 */
@Getter
@Setter
public class ProductDTO extends BasicDTO {

	private CategoryDTO category;
	private Double price;

	public ProductDTO() {
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}

}
