package com.zohancs.microservices.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "limits-service")
public class Configuration {
	private int maximum;
	private int minimum;	
}
