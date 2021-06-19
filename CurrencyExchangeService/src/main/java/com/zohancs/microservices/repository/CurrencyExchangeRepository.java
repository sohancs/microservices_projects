package com.zohancs.microservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zohancs.microservices.model.ExchangeValue;


public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue, Long> {
	
	Optional<ExchangeValue> findByFromAndTo(String from, String to);

}
