package com.stockmgt.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.stockmgt.dtos.BasicDTO;
import com.stockmgt.dtos.CategoryDTO;
import com.stockmgt.dtos.ProductDTO;
import com.stockmgt.entities.Category;
import com.stockmgt.entities.Product;

/**
 * 
 * @author Abderrahmen ISSA
 *
 */
public class DTOUtils {

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static BasicDTO convertToDTO(Serializable entity, String... ignoreProperties) {
		if (entity instanceof Category) {
			return convertToDTO((Category) entity, ignoreProperties);
		}
		if (entity instanceof Product) {
			return convertToDTO((Product) entity, ignoreProperties);
		}
		return null;
	}

	public static Serializable convertToEntity(BasicDTO dto) {
		if (dto instanceof CategoryDTO) {
			return convertToEntity((CategoryDTO) dto);
		}
		if (dto instanceof ProductDTO) {
			return convertToEntity((ProductDTO) dto);
		}
		return null;
	}

	/**
	 * 
	 * @param entities
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> convertToDTOs(List<? extends java.io.Serializable> entities, String... ignoreProperties) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}

		List<T> result = new ArrayList<>();
		entities.forEach(e -> result.add((T) convertToDTO(e, ignoreProperties)));

		return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @param category
	 * @return
	 */
	private static CategoryDTO convertToDTO(Category category, String... ignoreProperties) {
		if (category == null) {
			return null;
		}

		CategoryDTO result = new CategoryDTO();
		BeanUtils.copyProperties(category, result, ignoreProperties);

		return result;
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	private static ProductDTO convertToDTO(Product product, String... ignoreProperties) {
		if (product == null) {
			return null;
		}

		ProductDTO result = new ProductDTO();
		BeanUtils.copyProperties(product, result, ignoreProperties);

		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		if (ignoreList == null ||  ignoreList != null && !ignoreList.contains("category")) {
			Category category = product.getCategory();
			if (category != null) {
				result.setCategory(convertToDTO(category));
			}
		}

		return result;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	private static Serializable convertToEntity(ProductDTO dto) {
		if (dto == null) {
			return null;
		}

		Product result = new Product();
		BeanUtils.copyProperties(dto, result);

		if (dto.getCategory() != null) {
			result.setCategory((Category) convertToEntity(dto.getCategory()));
		}

		return result;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	private static Serializable convertToEntity(CategoryDTO dto) {
		if (dto == null) {
			return null;
		}

		Category result = new Category();
		BeanUtils.copyProperties(dto, result);

		return result;
	}
}
