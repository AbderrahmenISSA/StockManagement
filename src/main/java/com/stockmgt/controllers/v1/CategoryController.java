package com.stockmgt.controllers.v1;

import java.util.List;

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
import com.stockmgt.dtos.CategoryDTO;
import com.stockmgt.dtos.DTOUtils;
import com.stockmgt.entities.Category;

/**
 * CRUD rest webservice.
 */
@RestController
@RequestMapping(path = CategoryController.GATEGORIES_V1)
public class CategoryController {

	protected static final String GATEGORIES_V1 = "stockmgt/v1/gategories";

	@Autowired
	CategoryRespository categoryRespository;

	@GetMapping
	public List<CategoryDTO> index() {
		return DTOUtils.convertToDTOs(categoryRespository.findAll());
	}

	@GetMapping("/{id}")
	public CategoryDTO show(@PathVariable String id) {
		Integer CategoryId = Integer.parseInt(id);
		return (CategoryDTO) DTOUtils.convertToDTO(categoryRespository.findOne(CategoryId));
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable String id) {
		categoryRespository.delete(Integer.parseInt(id));
		return true;
	}

	@PostMapping
	public CategoryDTO create(@RequestBody CategoryDTO dto) {
		Category entity = (Category) DTOUtils.convertToEntity(dto);
		return (CategoryDTO) DTOUtils.convertToDTO(categoryRespository.save(entity));
	}

	@PutMapping("/{id}")
	public CategoryDTO update(@PathVariable String id, @RequestBody CategoryDTO dto) {
		dto.setId(Integer.parseInt(id));
		Category entity = (Category) DTOUtils.convertToEntity(dto);
		return (CategoryDTO) DTOUtils.convertToDTO(categoryRespository.save(entity));
	}

}