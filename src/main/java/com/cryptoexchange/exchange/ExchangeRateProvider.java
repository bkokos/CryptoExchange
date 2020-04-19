package com.cryptoexchange.exchange;

import com.cryptoexchange.exchange.calculator.ExchangeCalculator;
import com.cryptoexchange.exchange.data.ExchangeRateData;
import com.cryptoexchange.json.JsonParser;
import com.cryptoexchange.rate.RateProvider;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRateProvider {

	private final RateProvider rateProvider;

	public Map<String, ExchangeRateData> getExchangeRates(String baseCurrency, List<String> targetCurrencies, BigDecimal amount) {
		JSONObject ratesForCurrency = rateProvider.getRatesForCurrency(baseCurrency, targetCurrencies);

		JSONObject jsonRates = new JSONObject((LinkedHashMap) ratesForCurrency.get(baseCurrency.toLowerCase()));
		Map<String, BigDecimal> rates = JsonParser.parseValuesToBigDecimals(jsonRates);

		return rates.keySet()
				.parallelStream()
				.collect(Collectors.toMap(
						s -> s.toUpperCase(),
						s -> ExchangeCalculator.calculate(rates.get(s), amount),
						(v, v2) -> v2)
				);
	}
}
