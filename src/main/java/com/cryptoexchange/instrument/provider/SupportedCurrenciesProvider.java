package com.cryptoexchange.instrument.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportedCurrenciesProvider {
	private final RestTemplate restTemplate;

	public String getSupportedVsCurrencies() {
		ResponseEntity<List<String>> response =
				restTemplate.exchange("https://api.coingecko.com/api/v3/simple/supported_vs_currencies", HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});

		return String.join(",", response.getBody());
	}
}
