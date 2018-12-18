package com.stockmgt.controllers.v1;

import java.util.List;
import java.util.Optional;

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
import com.stockmgt.dtos.ProductDTO;
import com.stockmgt.entities.Category;
import com.stockmgt.entities.Product;
import com.stockmgt.utils.DTOUtils;

/**
 * CRUD rest webservice.
 * 
 * @author Abderrahmen ISSA
 *
 */
@RestController
@RequestMapping(path = ProductController.PRODUCT_V1)
public class ProductController {

	protected static final String PRODUCT_V1 = "stockmgt/v1";

	@Autowired
	private ProductRespository productRespository;

	@Autowired
	private CategoryRespository categoryRespository;

	@GetMapping("/products")
	public List<ProductDTO> index() {
		return DTOUtils.convertToDTOs(productRespository.findAll());
	}

	@GetMapping("/products/{id}")
	public ProductDTO show(@PathVariable Integer productId) {
		Optional<Product> product = productRespository.findById(productId);
		return (ProductDTO) DTOUtils.convertToDTO(product.get());
	}

	@PostMapping("/categories/{catId}/products")
	public ProductDTO create(@PathVariable(value = "catId") Integer catId, @RequestBody ProductDTO productDTO) {
		Optional<Category> category = categoryRespository.findById(catId);
		if (category == null) {
			return null;
		}

		Product entity = (Product) DTOUtils.convertToEntity(productDTO);
		entity.setCategory(category.get());
		return (ProductDTO) DTOUtils.convertToDTO(productRespository.save(entity));
	}

	@PutMapping("/categories/{catId}/products/{prodId}")
	public ProductDTO update(@PathVariable(value = "catId") Integer catId,
			@PathVariable(value = "prodId") Integer prodId, @RequestBody ProductDTO productDTO) {
		Optional<Category> category = categoryRespository.findById(catId);
		if (category == null) {
			return null;
		}

		Optional<Product> productFromDB = productRespository.findById(prodId);
		if (productFromDB == null) {
			return null;
		}
		productFromDB.get().setCategory(category.get());

		Product newProduct = (Product) DTOUtils.convertToEntity(productDTO);
		BeanUtils.copyProperties(newProduct, productFromDB, "id", "category");
		return (ProductDTO) DTOUtils.convertToDTO(productRespository.save(productFromDB.get()));
	}

	@DeleteMapping("/products/{id}")
	public boolean delete(@PathVariable Integer id) {
		productRespository.deleteById(id);
		return true;
	}

}