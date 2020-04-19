package com.cryptoexchange.instrument.provider;

import com.cryptoexchange.instrument.InstrumentDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstrumentDefProvider {

	private final RestTemplate restTemplate;

	@Value("${cryptocurrency.api.url}${cryptocurrency.api.coins}")
	private String coinsUrl;

	public Map<String, InstrumentDefinition> getInstrumentDefinitions() {
		ResponseEntity<List<InstrumentDefinition>> response =
				restTemplate.exchange(coinsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<InstrumentDefinition>>() {
				});
		List<InstrumentDefinition> instrumentDefinitions = response.getBody();

		return instrumentDefinitions != null ? instrumentDefinitions
				.stream()
				.collect(Collectors.toMap(InstrumentDefinition::getSymbol, instrumentDefinition -> instrumentDefinition, (t, t2) -> t2)) : null;
	}
}
