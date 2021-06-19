package com.zohancs.microservices.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zohancs.microservices.model.ExchangeValue;
import com.zohancs.microservices.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	private Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment environment;
	@Autowired
	private CurrencyExchangeRepository repo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriewExchangeValue(@PathVariable String from, @PathVariable String to) throws Exception {
		Optional<ExchangeValue> exchangeValueOptional = repo.findByFromAndTo(from, to);
		if(!exchangeValueOptional.isPresent()) {
			throw new Exception("currency exchange not available for given currencies");
		}
		ExchangeValue exchangeValue = exchangeValueOptional.get();
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		LOGGER.info("{}",exchangeValue);
		return exchangeValue;
	}
}
