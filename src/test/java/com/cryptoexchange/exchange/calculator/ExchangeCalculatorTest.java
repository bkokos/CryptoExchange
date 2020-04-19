package com.cryptoexchange.exchange.calculator;

import com.cryptoexchange.exchange.data.ExchangeRateData;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeCalculatorTest {

	@Test
	public void shouldCalculateExchangeRateAndFeeCorrectly() {
		BigDecimal rate = new BigDecimal("123.654");
		BigDecimal amount = new BigDecimal("268");
		BigDecimal expextedResult = new BigDecimal("33139.272");
		BigDecimal expectedFee = new BigDecimal("331.39272");

		ExchangeRateData calculatedData = ExchangeCalculator.calculate(rate, amount);

		assertEquals(calculatedData.getResult(), expextedResult);
		assertEquals(calculatedData.getFee(), expectedFee);
	}

	@Test
	public void shouldRoundResultCorrectly() {
		BigDecimal rate = new BigDecimal("123.65489665689786");
		BigDecimal amount = BigDecimal.TEN;

		ExchangeRateData calculatedData = ExchangeCalculator.calculate(rate, amount);
		assertEquals(calculatedData.getResult().scale(), 8);
		assertEquals(calculatedData.getResult(), new BigDecimal("1236.54896657"));
		assertEquals(calculatedData.getFee(), new BigDecimal("12.36548967"));
	}
}