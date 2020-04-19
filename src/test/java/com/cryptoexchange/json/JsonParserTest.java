package com.cryptoexchange.json;

import com.cryptoexchange.exchange.data.ExchangeRateData;
import com.cryptoexchange.instrument.InstrumentDefinition;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonParserTest {

	@Test
	public void parseValuesToBigDecimals() {
		Map<String, Number> valuesToMap = new HashMap<>();
		valuesToMap.put("BTC", 2345);
		valuesToMap.put("ETH", 234.54);

		JSONObject josn = new JSONObject(valuesToMap);
		Map<String, BigDecimal> stringBigDecimalMap = JsonParser.parseValuesToBigDecimals(josn);

		assertTrue(stringBigDecimalMap.containsKey("BTC"));
		assertTrue(stringBigDecimalMap.containsKey("ETH"));

		assertEquals(stringBigDecimalMap.get("BTC"), new BigDecimal("2345"));
		assertEquals(stringBigDecimalMap.get("ETH"), new BigDecimal("234.54"));
	}

}