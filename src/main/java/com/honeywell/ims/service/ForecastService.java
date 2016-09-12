package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.honeywell.ims.api.weather.Forecast;

@Component
public class ForecastService {

	private Logger logger = LoggerFactory.getLogger(ForecastService.class);

	private static final String URL = "http://api.openweathermap.org/data/2.5/forecast?id=3078610&APPID=76689c24d33b982520e19e323e4f8add";

	public Forecast getActualForecast() {
		RestTemplate restTemplate = new RestTemplate();
		Forecast forecast = restTemplate.getForObject(getForecastServiceUrl(), Forecast.class);
		logger.info(forecast.toString());
		return forecast;
	}

	private String getForecastServiceUrl() {
		return URL;
	}
}
