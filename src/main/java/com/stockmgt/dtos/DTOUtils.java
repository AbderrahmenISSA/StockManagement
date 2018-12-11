package com.stockmgt.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.stockmgt.entities.Category;
import com.stockmgt.entities.Product;

/**
 * 
 * @author ext-aissa
 *
 */
public class DTOUtils {

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static BasicDTO convertToDTO(Serializable entity) {
		if (entity instanceof Category) {
			return convertToDTO((Category) entity);
		}
		if (entity instanceof Product) {
			return convertToDTO((Product) entity);
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
	public static <T> List<T> convertToDTOs(List<? extends java.io.Serializable> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}

		List<T> result = new ArrayList<>();
		entities.forEach(e -> result.add((T) convertToDTO(e)));

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
	private static CategoryDTO convertToDTO(Category category) {
		if (category == null) {
			return null;
		}

		CategoryDTO result = new CategoryDTO();
		BeanUtils.copyProperties(category, result);

		return result;
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	private static ProductDTO convertToDTO(Product product) {
		if (product == null) {
			return null;
		}

		ProductDTO result = new ProductDTO();
		BeanUtils.copyProperties(product, result);

		Category category = product.getCategory();
		if (category != null) {
			result.setCategory(convertToDTO(category));
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