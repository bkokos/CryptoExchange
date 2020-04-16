package com.cryptoexchange.json;

import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.Map;

public class JsonParser {

	public static JSONObject parseRateResponse(String baseCurrency, Map<String, BigDecimal> currenciesWithRates) {
		JSONObject response = new JSONObject();
		response.put("source", baseCurrency);

		JSONObject rates = new JSONObject(currenciesWithRates);
		response.put("rates", rates);

		return response;
	}

	public static JSONObject parseExchangeResponse(String baseCurrency){
		JSONObject response = new JSONObject();
		response.put("from", baseCurrency);

		return response;
	}
}
