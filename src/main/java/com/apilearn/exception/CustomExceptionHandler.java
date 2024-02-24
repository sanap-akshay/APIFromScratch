package com.apilearn.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.apilearn.util.Utilities;
import static com.apilearn.util.Constants.*;

@ControllerAdvice
public class CustomExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Utilities utilities;
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameterException(MissingServletRequestParameterException requestParameterException){
		logger.error("Raised Missing Servlet Request Parameter Exception : {}", requestParameterException.getMessage());
		Map<String, Object> responseMap = utilities.errorResponseFormatter(404, requestParameterException.getMessage());
		return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException argumentTypeMismatchException, WebRequest request ){
		logger.error("Raised Method Argument TypeMismatch Exception : {}", argumentTypeMismatchException.getMessage());
		Map<String, Object> data = new HashMap<>();
		data.put(MESSAGE, argumentTypeMismatchException.getMessage());
		data.put(DESCRIPTION, request.getDescription(false));
		data.put(TIMESTAMP, new Date());
		Map<String, Object> responseMap = utilities.errorResponseFormatter(400, data);
		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
	}
}
