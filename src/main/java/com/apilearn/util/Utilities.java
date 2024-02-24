package com.apilearn.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import static com.apilearn.util.Constants.*;

@Component
public class Utilities {

	public Map<String, Object> successResponseFormatter(int statusCode, Object data) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> meta = new HashMap<>();

		meta.put(STATUS_CODE, statusCode);
		map.put(DATA, data);
		map.put(META, meta);

		return map;
	}
	
	public Map<String, Object> errorResponseFormatter(int statusCode, String msg) {
		Map<String, Object> map = new HashMap<>();

		map.put(STATUS_CODE, statusCode);
		map.put(MESSAGE, msg);
		

		return map;
	}
	
	public Map<String, Object> errorResponseFormatter(int statusCode, Object data) {
		Map<String, Object> map = new HashMap<>();

		map.put(STATUS_CODE, statusCode);
		map.put(DATA, data);
		

		return map;
	}

}
