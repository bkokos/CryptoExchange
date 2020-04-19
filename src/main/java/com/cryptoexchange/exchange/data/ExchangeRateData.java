package com.cryptoexchange.exchange.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class ExchangeRateData {

	private final BigDecimal rate;
	private final BigDecimal amount;
	private final BigDecimal result;
	private final BigDecimal fee;
}
