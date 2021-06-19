package com.zohancs.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zohancs.microservices.config.Configuration;
import com.zohancs.microservices.model.LimitConfiguration;

@RestController
public class LimitConfigurationController {
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retriewLimitConfiguration()
	{
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallBackRetriewConfiguration")
	public LimitConfiguration retriewConfiguration()
	{
		throw new RuntimeException("Service Unavailable");
	}

	//called incase of exception in retriewConfiguration. Acts as fault tolerance
	public LimitConfiguration fallBackRetriewConfiguration() {
		return new LimitConfiguration(7856,1010);
	}
}
