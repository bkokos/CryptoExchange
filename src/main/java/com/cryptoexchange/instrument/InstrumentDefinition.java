package com.cryptoexchange.instrument;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class InstrumentDefinition {
	private final String id;
	private final String symbol;
	private final String name;
}
