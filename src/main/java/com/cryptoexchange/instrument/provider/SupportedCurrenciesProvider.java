package com.cryptoexchange.instrument.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${cryptocurrency.api.url}${cryptocurrency.api.supported-currencies}")
	String supportedCurrenciesUrl;

	public String getSupportedVsCurrencies() {
		ResponseEntity<List<String>> response =
				restTemplate.exchange(supportedCurrenciesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});

		return String.join(",", response.getBody());
	}
}
