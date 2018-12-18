package com.stockmgt.controllers.v3;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockmgt.daos.ProductRespository;
import com.stockmgt.dtos.BasicDTO;
import com.stockmgt.utils.DTOUtils;
import com.stockmgt.utils.FieldJsonMapper;

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

	@GetMapping("/products")
	public ResponseEntity<?> index(@RequestParam("select") String fieldsString) throws JSONException {
		String[] fields = fieldsString.split(",");
		List<BasicDTO> list = DTOUtils.convertToDTOs(productRespository.findAll());
		List<JSONObject> result = FieldJsonMapper.toJson(list, fields);
		return new ResponseEntity<List<JSONObject>>(result, HttpStatus.OK);
	}

}