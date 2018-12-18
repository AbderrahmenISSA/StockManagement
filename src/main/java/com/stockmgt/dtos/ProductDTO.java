package com.stockmgt.dtos;
// Generated 10 d�c. 2018 14:48:45 by Hibernate Tools 5.3.6.Final

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfert Object to @Product
 * 
 * @author Abderrahmen ISSA
 * 
 */
@Getter
@Setter
public class ProductDTO extends BasicDTO {

	private CategoryDTO category;

	private String productName;
	private String productCode;
	private Integer stock;
	private Double priceHt;
	private Double priceTtc;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
	private Date createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
	private Date updatedAt;

	public ProductDTO() {
	}

	@Override
	public String toString() {
		return "Product [name=" + productName + ", price=" + priceTtc + "]";
	}

}
