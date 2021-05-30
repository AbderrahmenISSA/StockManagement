package com.stockmgt.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.stockmgt.controllers.v1.CategoryController;
import com.stockmgt.daos.CategoryRespository;
import com.stockmgt.dtos.CategoryDTO;
import com.stockmgt.entities.Category;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerMockitoTest {

	@MockBean
	private CategoryRespository categoryRespository;

	@Autowired
	private CategoryController categoryController;

	@Test
	public void testCreation() {
		// given
		given(categoryRespository.save(any(Category.class))).willReturn(userEntity(1));

		// when
		CategoryDTO dto = new CategoryDTO("Boissons");
		ResponseEntity<?> response = categoryController.create(dto);

		// then
		CategoryDTO result = (CategoryDTO) response.getBody();
		Assertions.assertEquals(result.getId(), 1);
	}

	private Category userEntity(Integer id) {
		return new Category(id, "Boissons", new Date());
	}
}
