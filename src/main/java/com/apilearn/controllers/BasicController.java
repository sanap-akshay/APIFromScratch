package com.apilearn.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apilearn.exception.BusinessException;
import com.apilearn.service.FibonacciSeries;
import com.apilearn.util.Utilities;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class BasicController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FibonacciSeries fibonacciSeries;

	@Autowired
	private Utilities utilities;

	@GetMapping("/FibonacciSeries")
	public ResponseEntity<Map<String, Object>> getFibonacciSeries(@RequestParam Integer n,
			HttpServletResponse response) {
		logger.info("Inside BasicController : getFibonacciSeries and given number is n : {}", n);
		try {
			logger.info("calling fibonacciSeries.fibonacciSeries");
			Integer result = fibonacciSeries.fibonacciSeries(n);
			logger.info("Result of fibonacciSeries.fibonacciSeries : {}", result);
			Map<String, Object> responseMap = utilities.successResponseFormatter(200, result);
			return new ResponseEntity<>(responseMap, HttpStatus.OK);

		} catch (BusinessException bex) {
			logger.error("Business Exception raised Message : {}", bex.getErrorMsg());
			Map<String, Object> responseMap = utilities.errorResponseFormatter(bex.getErrorCode(), bex.getErrorMsg());
			return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			logger.error("Exception raised Message : {}", e.getMessage());
			Map<String, Object> responseMap = utilities.errorResponseFormatter(400, e.getMessage());
			return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
		}

	}

}
