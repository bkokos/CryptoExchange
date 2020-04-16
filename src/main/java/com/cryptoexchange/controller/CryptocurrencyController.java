package com.cryptoexchange.controller;

import com.cryptoexchange.rate.RateProvider;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CryptocurrencyController {

	private final RateProvider rateProvider;

	@GetMapping("/{currency}")
	public JSONObject getRate(@PathVariable String currency,
			@RequestParam(value = "filter", required = false) List<String> filters) {

		return rateProvider.getRatesForCurrency(currency, filters);
	}
}
