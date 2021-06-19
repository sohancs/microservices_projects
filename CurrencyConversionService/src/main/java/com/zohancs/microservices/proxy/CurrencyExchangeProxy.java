package com.zohancs.microservices.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zohancs.microservices.model.CurrencyConversionBean;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")  //use to talk to other external micro-services
//@FeignClient(name = "currency-exchange-service")  
//instead of directly calling to currency-exchange-service. We are calling through zuul-api-gateway.
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")// client side load balancing
public interface CurrencyExchangeProxy {

	/*
	 * @GetMapping("/currency-exchange/from/{from}/to/{to}") public
	 * CurrencyConversionBean retriewExchangeValue(@PathVariable String
	 * from, @PathVariable String to) throws Exception;
	 */
	
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retriewExchangeValue(@PathVariable String from, @PathVariable String to) throws Exception;
}
