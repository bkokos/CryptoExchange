package com.cryptoexchange.exchange.calculator;

import com.cryptoexchange.exchange.data.ExchangeRateData;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Exchange calculator with precision to 1 satoshi = 0.00000001 BTC.
 */
public class ExchangeCalculator {

	private static final MathContext mc = new MathContext(8, RoundingMode.HALF_UP);
	private static final BigDecimal FEE = new BigDecimal("0.01");

	public static ExchangeRateData calculate(BigDecimal rate, BigDecimal amount) {
		BigDecimal result = calculateResult(rate, amount);
		return new ExchangeRateData(rate,
				amount,
				result,
				calculateFee(result));
	}

	private static BigDecimal calculateResult(BigDecimal rate, BigDecimal amount) {
		return rate.multiply(amount, mc);
	}
	private static BigDecimal calculateFee(BigDecimal value) {
		return value.multiply(FEE, mc);
	}
}
