package com.cryptoexchange.rate;

import com.cryptoexchange.instrument.InstrumentDefinition;
import com.cryptoexchange.instrument.provider.InstrumentDefProvider;
import com.cryptoexchange.instrument.provider.SupportedCurrenciesProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateProvider {

	private final InstrumentDefProvider instrumentDefProvider;
	private final SupportedCurrenciesProvider supportedCurrenciesProvider;

	private final RestTemplate restTemplate;

	@Value("${cryptocurrency.api.url}${cryptocurrency.api.rate}")
	String ratesUrl;

	public JSONObject getRatesForCurrency(String currency, List<String> filters) {

		String vsCurrencies = "";
		if (filters == null || filters.size() < 1) {
			vsCurrencies = supportedCurrenciesProvider.getSupportedVsCurrencies();
		} else {
			vsCurrencies = String.join(",", filters);
		}

		Map<String, InstrumentDefinition> instrumentDefinitions = instrumentDefProvider.getInstrumentDefinitions();
		InstrumentDefinition baseCurrencyDef = instrumentDefinitions.get(currency.toLowerCase());

		JSONObject response = restTemplate.getForObject(URI.create(format(ratesUrl, baseCurrencyDef.getId(), vsCurrencies)), JSONObject.class);

		JSONObject formattedResponse = new JSONObject();
		formattedResponse.put(baseCurrencyDef.getSymbol(), response.get(baseCurrencyDef.getId())); // TODO: move to formatter service

		return formattedResponse;
	}

}
