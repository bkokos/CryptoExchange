package com.cryptoexchange.rate;

import com.cryptoexchange.instrument.InstrumentDefinition;
import com.cryptoexchange.instrument.provider.InstrumentDefProvider;
import com.cryptoexchange.instrument.provider.SupportedCurrenciesProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateProvider {

	private final InstrumentDefProvider instrumentDefProvider;
	private final SupportedCurrenciesProvider supportedCurrenciesProvider;

	private final RestTemplate restTemplate;

	public JSONObject getRatesForCurrency(String currency, List<String> filters) {

		String vsCurrencies = "";
		if (filters == null || filters.size() < 1) {
			vsCurrencies = supportedCurrenciesProvider.getSupportedVsCurrencies();
		} else {
			vsCurrencies = String.join(",", filters);
		}
		log.info("Start");
		Map<String, InstrumentDefinition> instrumentDefinitions = instrumentDefProvider.getInstrumentDefinitions();
		log.info("Stop");
		InstrumentDefinition baseCurrencyDef = instrumentDefinitions.get(currency.toLowerCase());

		ResponseEntity<JSONObject> response = restTemplate
				.exchange("https://api.coingecko.com/api/v3/simple/price?ids=" + baseCurrencyDef.getId() + "&vs_currencies=" + vsCurrencies
						, HttpMethod.GET
						, null
						, new ParameterizedTypeReference<JSONObject>() {
						});

		return response.getBody();
	}

}
