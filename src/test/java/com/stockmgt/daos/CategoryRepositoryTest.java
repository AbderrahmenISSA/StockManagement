package com.stockmgt.daos;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stockmgt.entities.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRespository categoryRespository;

	@Test
	public void testFindByName() {
		Category category = categoryRespository.findByCategoryName("Charcuterie");
		Assertions.assertEquals(category.getId(), 2);
	}

}
