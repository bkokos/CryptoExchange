package com.cryptoexchange.json;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

public class JsonParserTest {

	@Test
	public void shouldParseJSONForRatesResponse() {
		BigDecimal ethRate = valueOf(0.21);
		BigDecimal litRate = valueOf(0.43);
		BigDecimal plnRate = valueOf(34.24);
		BigDecimal usdRate = valueOf(23450.21);
		BigDecimal usdtRate = valueOf(56767540.21);

		Map<String, BigDecimal> rates = new LinkedHashMap<>();
		rates.put("ETH", ethRate);
		rates.put("LIT", litRate);
		rates.put("PLN", plnRate);
		rates.put("USD", usdRate);
		rates.put("USDT", usdtRate);

		JSONObject response = JsonParser.parseRateResponse("BTC", rates);

		assertEquals(response.get("source"), "BTC");

		JSONObject ratesFromJson = (JSONObject) response.get("rates");

		assertEquals(ratesFromJson.get("ETH"), ethRate);
		assertEquals(ratesFromJson.get("LIT"), litRate);
		assertEquals(ratesFromJson.get("PLN"), plnRate);
		assertEquals(ratesFromJson.get("USD"), usdRate);
		assertEquals(ratesFromJson.get("USDT"), usdtRate);
	}
}