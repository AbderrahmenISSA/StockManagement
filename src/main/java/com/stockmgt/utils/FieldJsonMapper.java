package com.stockmgt.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.stockmgt.dtos.BasicDTO;

/**
 * @author Abderrahmen ISSA
 */
public class FieldJsonMapper {

	/**
	 * @param dto    object containing BasicDTO information
	 * @param fields fields information to get
	 * 
	 * @return json of required fields
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws JSONException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static JSONObject toJson(BasicDTO dto, String[] fields) throws IllegalArgumentException,
			IllegalAccessException, JSONException, NoSuchFieldException, SecurityException {
		JSONObject jo = new JSONObject();

		for (int i = 0; i < fields.length; i++) {
			Field declaredField = dto.getClass().getDeclaredField(fields[i]);
			declaredField.setAccessible(true);
			Object value = declaredField.get(dto);
			jo.put(declaredField.getName(), value == null ? "" : value.toString());
		}

		return jo;
	}

	/**
	 * @param dtos   object containing list of BasicDTO information
	 * @param fields fields information to get
	 * 
	 * @return json of required fields
	 */
	public static List<JSONObject> toJson(List<BasicDTO> dtos, String[] fields) {
		List<JSONObject> result = new ArrayList<>();
		for (BasicDTO dto : dtos) {
			try {
				result.add(toJson(dto, fields));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
