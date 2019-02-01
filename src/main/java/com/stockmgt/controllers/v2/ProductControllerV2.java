package com.stockmgt.controllers.v2;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.stockmgt.errors.RestError;
import com.stockmgt.utils.DTOUtils;

/**
 * We implement here recommended HTTP status codes.
 * 
 * @author Abderrahmen ISSA
 * 
 */
@RestController
@RequestMapping(path = ProductControllerV2.PRODUCT_V2)
public class ProductControllerV2 {

	protected static final String PRODUCT_V2 = "stockmgt/v2";

	@Autowired
	private ProductRespository productRespository;

	@Autowired
	private CategoryRespository categoryRespository;

	@CrossOrigin
	@GetMapping("/products")
	public List<ProductDTO> index() {
		return DTOUtils.convertToDTOs(productRespository.findAll());
	}

	@CrossOrigin
	@GetMapping("/products/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Optional<Product> product = productRespository.findById(id);
		if (!product.isPresent()) {
			RestError error = new RestError();
			error.setError("Entity not found.");
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage("No class " + Category.class.getName() + " entity with id " + id + " exists");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>((ProductDTO) DTOUtils.convertToDTO(product.get()), HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("/categories/{catId}/products")
	public ResponseEntity<?> create(@PathVariable(value = "catId") Integer catId, @RequestBody ProductDTO productDTO) {
		Optional<Category> category = categoryRespository.findById(catId);
		if (!category.isPresent()) {
			return null;
		}

		Product entity = (Product) DTOUtils.convertToEntity(productDTO);
		entity.setCategory(category.get());
		entity.setCreatedAt(Calendar.getInstance().getTime());
		ProductDTO result = (ProductDTO) DTOUtils.convertToDTO(productRespository.save(entity));
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping("/categories/{catId}/products/{prodId}")
	public ResponseEntity<?> update(@PathVariable(value = "catId") Integer catId,
			@PathVariable(value = "prodId") Integer prodId, @RequestBody ProductDTO productDTO) {
		Optional<Category> category = categoryRespository.findById(catId);
		if (!category.isPresent()) {
			return null;
		}

		Optional<Product> productFromDB = productRespository.findById(prodId);
		if (!productFromDB.isPresent()) {
			return null;
		}
		productFromDB.get().setCategory(category.get());
		productFromDB.get().setUpdatedAt(Calendar.getInstance().getTime());

		Product newProduct = (Product) DTOUtils.convertToEntity(productDTO);
		BeanUtils.copyProperties(newProduct, productFromDB, "id", "category");
		ProductDTO result = (ProductDTO) DTOUtils.convertToDTO(productRespository.save(productFromDB.get()));
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@CrossOrigin
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			productRespository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			RestError error = new RestError();
			error.setError("Entity not found or has been deleted.");
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage("No class " + Category.class.getName() + " entity with id " + id + " exists");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}