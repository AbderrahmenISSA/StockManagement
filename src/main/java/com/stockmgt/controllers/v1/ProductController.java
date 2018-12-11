package com.stockmgt.controllers.v1;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmgt.daos.CategoryRespository;
import com.stockmgt.daos.ProductRespository;
import com.stockmgt.dtos.DTOUtils;
import com.stockmgt.dtos.ProductDTO;
import com.stockmgt.entities.Category;
import com.stockmgt.entities.Product;

@RestController
@RequestMapping(path = ProductController.PRODUCT_V1)
public class ProductController {

	protected static final String PRODUCT_V1 = "stockmgt/v1";

	@Autowired
	ProductRespository productRespository;

	@Autowired
	CategoryRespository categoryRespository;

	@GetMapping("/products")
	public List<ProductDTO> index() {
		return DTOUtils.convertToDTOs(productRespository.findAll());
	}

	@GetMapping("/products/{id}")
	public ProductDTO show(@PathVariable String id) {
		Integer productId = Integer.parseInt(id);
		return (ProductDTO) DTOUtils.convertToDTO(productRespository.findOne(productId));
	}

	@PostMapping("/categories/{catId}/products")
	public ProductDTO create(@PathVariable(value = "catId") Integer catId, @RequestBody ProductDTO productDTO) {
		Category category = categoryRespository.findOne(catId);
		if (category == null) {
			return null;
		}

		Product entity = (Product) DTOUtils.convertToEntity(productDTO);
		entity.setCategory(category);
		return (ProductDTO) DTOUtils.convertToDTO(productRespository.save(entity));
	}

	@PutMapping("/categories/{catId}/products/{prodId}")
	public ProductDTO update(@PathVariable(value = "catId") Integer catId,
			@PathVariable(value = "prodId") Integer prodId, @RequestBody ProductDTO productDTO) {
		Category category = categoryRespository.findOne(catId);
		if (category == null) {
			return null;
		}

		Product productFromDB = productRespository.findOne(prodId);
		if (productFromDB == null) {
			return null;
		}
		productFromDB.setCategory(category);

		Product newProduct = (Product) DTOUtils.convertToEntity(productDTO);
		BeanUtils.copyProperties(newProduct, productFromDB, "id", "category");
		return (ProductDTO) DTOUtils.convertToDTO(productRespository.save(productFromDB));
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable String id) {
		productRespository.delete(Integer.parseInt(id));
		return true;
	}

}