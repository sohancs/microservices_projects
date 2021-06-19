package com.zohancs.microservices.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class ExchangeValue {

	@NonNull
	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	@Column(name = "currency_from")
	private String from;
	@NonNull
	@Column(name = "currency_to")
	private String to;
	
	@NonNull
	private BigDecimal conversionMultiple;
	private int port;
}
