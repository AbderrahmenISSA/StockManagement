package com.stockmgt.controllers.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmgt.daos.CategoryRespository;
import com.stockmgt.dtos.CategoryDTO;
import com.stockmgt.dtos.DTOUtils;
import com.stockmgt.entities.Category;

/**
 * We implement here recommended HTTP status codes.
 */
@RestController
@RequestMapping(path = CategoryControllerV2.GATEGORIES_V2)
public class CategoryControllerV2 {

	protected static final String GATEGORIES_V2 = "stockmgt/v2/gategories";

	@Autowired
	CategoryRespository categoryRespository;

	@GetMapping
	public List<CategoryDTO> index() {
		return DTOUtils.convertToDTOs(categoryRespository.findAll());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CategoryDTO dto) {
		Category entity = (Category) DTOUtils.convertToEntity(dto);
		CategoryDTO result = (CategoryDTO) DTOUtils.convertToDTO(categoryRespository.save(entity));
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody CategoryDTO dto) {
		dto.setId(Integer.parseInt(id));
		Category entity = (Category) DTOUtils.convertToEntity(dto);
		CategoryDTO result = (CategoryDTO) DTOUtils.convertToDTO(categoryRespository.save(entity));
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		Integer CategoryId = Integer.parseInt(id);
		Category category = categoryRespository.findOne(CategoryId);
		if (category == null) {
			RestError error = new RestError();
			error.setError("Entity not found.");
			error.setError_code(HttpStatus.NOT_FOUND.value());
			error.setError_description("No class " + Category.class.getName() + " entity with id " + id + " exists");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>((CategoryDTO) DTOUtils.convertToDTO(category), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			categoryRespository.delete(Integer.parseInt(id));
		} catch (EmptyResultDataAccessException ex) {
			RestError error = new RestError();
			error.setError("Entity not found or has been deleted.");
			error.setError_code(HttpStatus.NOT_FOUND.value());
			error.setError_description("No class " + Category.class.getName() + " entity with id " + id + " exists");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}