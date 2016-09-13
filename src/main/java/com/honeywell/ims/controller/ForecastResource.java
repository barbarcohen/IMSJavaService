package com.honeywell.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.honeywell.ims.api.weather.Forecast;
import com.honeywell.ims.api.weather.Weather;
import com.honeywell.ims.service.ForecastService;
import com.honeywell.ims.service.SettingsService;

/**
 * Created by h134602 on 9/12/2016.
 */
@RestController
@RequestMapping("/forecast")
public class ForecastResource {

	@Autowired
	private ForecastService forecastService;

	@Autowired
	private SettingsService settingsService;

	@RequestMapping("/actual")
	public Forecast getAll() {
		Forecast forecast = forecastService.getLatestForecast();
		if (forecast == null) {
			forecast = forecastService.fetchForecast();
		}
		return forecast;
	}

	@RequestMapping("/weather")
	public List<Weather> getWeather(@RequestParam(name = "hours",required = false) Integer hours){
		if(hours ==null){
			hours = settingsService.getSettings(null).getForecastHours();
		}
		return forecastService.getWeatherPercentage(hours);
	}
}
