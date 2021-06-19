package com.zohancs.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zohancs.microservices.model.CurrencyConversionBean;
import com.zohancs.microservices.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	private Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retriewCurrencyConversion(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity = 
				new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
						CurrencyConversionBean.class, uriVariables);
		
		CurrencyConversionBean responseBean = responseEntity.getBody();
		
		return new CurrencyConversionBean(responseBean.getId(),responseBean.getFrom(),responseBean.getTo(),
				responseBean.getConversionMultiple(), quantity,
				quantity.multiply(responseBean.getConversionMultiple()),responseBean.getPort());
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retriewCurrencyConversionFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean responseBean = null;
		try {
			responseBean = proxy.retriewExchangeValue(from, to);
			LOGGER.info("{}",responseBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new CurrencyConversionBean(responseBean.getId(),responseBean.getFrom(),responseBean.getTo(),
				responseBean.getConversionMultiple(), quantity,
				quantity.multiply(responseBean.getConversionMultiple()),responseBean.getPort());
	}
}
