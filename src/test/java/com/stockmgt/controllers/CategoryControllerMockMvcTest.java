package com.stockmgt.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmgt.daos.CategoryRespository;
import com.stockmgt.dtos.CategoryDTO;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerMockMvcTest {

	@MockBean
	private CategoryRespository categoryRespository;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCreation() throws Exception {
		CategoryDTO dto = new CategoryDTO("Boissons");
		
		mockMvc.perform(post("/stockmgt/api/v1/categories")
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(objectMapper.writeValueAsString(dto))						
				   .accept(MediaType.APPLICATION_JSON))
				   .andExpect(status().isCreated());
	}

}
