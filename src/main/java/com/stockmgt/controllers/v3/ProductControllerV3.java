package com.stockmgt.controllers.v3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockmgt.daos.ProductRespository;
import com.stockmgt.dtos.ProductDTO;
import com.stockmgt.entities.Category;
import com.stockmgt.entities.Product;
import com.stockmgt.errors.RestError;
import com.stockmgt.utils.DTOUtils;

/**
 * We implement here partial JSON response.
 * 
 * @author Abderrahmen ISSA
 * 
 */
@RestController
@RequestMapping(path = ProductControllerV3.PRODUCT_V3)
public class ProductControllerV3 {

	protected static final String PRODUCT_V3 = "stockmgt/v3";

	@Autowired
	private ProductRespository productRespository;

	@CrossOrigin
	@GetMapping("/products")
	public List<ProductDTO> index(@RequestParam(name="select", required=false) String fieldsString) throws JSONException {
		String[] ignoreProperties = getIgnoreProperties(fieldsString);
		return DTOUtils.convertToDTOs(productRespository.findAll(), ignoreProperties);
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

	/**
	 * 
	 * @param fieldsString
	 */
	private String[]  getIgnoreProperties(String fieldsString) {
		if (StringUtils.isEmpty(fieldsString)) {
			return null;
		}
		String[] fields = fieldsString.split(",");
		List<String> fieldsList = (fields != null ? Arrays.asList(fields) : null);
		
		List<String> fieldsToIgnore = ProductDTO.fields
				.stream()
				.filter(field -> !fieldsList.contains(field))
				.collect(Collectors.toList());
		return fieldsToIgnore.stream().toArray(String[]::new);
	}
}