package com.cryptoexchange.json;

import com.cryptoexchange.instrument.InstrumentDefinition;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {

	public static Map<String, BigDecimal> parseValuesToBigDecimals(JSONObject json) {
		Map<String, BigDecimal> results = new HashMap<>();
		json.keySet().forEach(
				k -> results.put(String.valueOf(k), new BigDecimal(String.valueOf(json.get(k)))));

		return results;
	}

	public static JSONObject parseRatesResponse(InstrumentDefinition baseCurrencyDef, JSONObject response) {
		JSONObject formattedResponse = new JSONObject();
		formattedResponse.put(baseCurrencyDef.getSymbol(), response.get(baseCurrencyDef.getId()));
		return formattedResponse;
	}

}
