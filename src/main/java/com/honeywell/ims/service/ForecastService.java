package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.honeywell.ims.Constants;
import com.honeywell.ims.api.weather.Forecast;
import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.dao.ForecastDao;
import com.honeywell.ims.domain.RainProbability;

@Component
public class ForecastService {

	private Logger logger = LoggerFactory.getLogger(ForecastService.class);

	@Autowired
	private ForecastDao forecastDao;

	public Forecast fetchForecast() {
		logger.info("Requesting fresh forecast from URL: {} ", Constants.FORECAST_URL);

		RestTemplate restTemplate = new RestTemplate();
		Forecast forecast = restTemplate.getForObject(Constants.FORECAST_URL, Forecast.class);
		forecastDao.saveForecast(forecast);

		return forecast;
	}

	public Forecast getLatestForecast(){
		logger.info("Requesting cached forecast data");

		Forecast forecast = forecastDao.getLatestForecast();
		if(forecast == null){
			logger.info("Forecast not cached yet. Going for fresh one... ");

			forecast = fetchForecast();
		}
		return forecast;
	}

	/**
	 *
	 * @return TRUE if should rain, FALSE otherwise
	 */
	public RainProbability getRainProbability(Settings settings){
		Forecast forecast = getLatestForecast();
		//TODO get from foreccaast data
		return new RainProbability(50,10);
	}
}
