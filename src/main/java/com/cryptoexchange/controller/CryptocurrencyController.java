package com.cryptoexchange.controller;

import com.cryptoexchange.exchange.ExchangeRateProvider;
import com.cryptoexchange.exchange.data.ExchangeRateData;
import com.cryptoexchange.rate.RateProvider;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CryptocurrencyController {

	private final RateProvider rateProvider;
	private final ExchangeRateProvider exchangeRateProvider;

	@GetMapping("/{currency}")
	public JSONObject getRate(@PathVariable String currency,
			@RequestParam(value = "filter", required = false) List<String> filters) {

		return rateProvider.getRatesForCurrency(currency, filters);
	}

	@GetMapping("/exchange")
	public JSONObject getExchangeRate() {
		Map<String, ExchangeRateData> exchangeRates = exchangeRateProvider.getExchangeRates("BTC", Arrays.asList("ETH,PLN"), BigDecimal.valueOf(12));
		JSONObject response = new JSONObject();
		response.put("BTC", exchangeRates);

		return response;
	}
}
