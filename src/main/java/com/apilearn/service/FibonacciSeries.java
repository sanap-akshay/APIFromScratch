package com.apilearn.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.apilearn.exception.BusinessException;

@Service
public class FibonacciSeries {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Integer fibonacciSeries(Integer n) {
		logger.info("Inside FibonacciSeries : fibonacciSeries and given number is n : {}", n);
		if (n == null || n <= 0) {
			logger.info("Given number is null or negative or zero");
			throw new BusinessException(601, "Given number is null or negative or zero");
		}
		try {
			int n1 = 0;
			int n2 = 1;
			int n3;

			List<Integer> fibonacci = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				n3 = n1 + n2;
				fibonacci.add(n1);
				n1 = n2;
				n2 = n3;
			}
			logger.info("Outside Of FibonacciSeries : fibonacciSeries");
			return fibonacci.get(fibonacci.size() - 1);
		} catch (Exception e) {
			logger.error("Exception Raised while calculating fibonacci series");
			throw new BusinessException(602, "Exception Raised while calculating fibonacci series");

		}
	}

}
