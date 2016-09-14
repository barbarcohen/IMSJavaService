package com.honeywell.ims.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.honeywell.ims.Constants;
import com.honeywell.ims.api.weather.Data;
import com.honeywell.ims.api.weather.Forecast;
import com.honeywell.ims.api.weather.Weather;
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

	public Forecast getLatestForecast() {
		logger.info("Requesting cached forecast data");

		Forecast forecast = forecastDao.getLatestForecast();
		if (forecast == null) {
			logger.info("Forecast not cached yet. Going for fresh one... ");

			forecast = fetchForecast();
		}
		return forecast;
	}

	/**
	 * @return TRUE if should rain, FALSE otherwise
	 */
	public RainProbability getRainProbability(Settings settings) {
		Forecast forecast = getLatestForecast();

		//TODO get from foreccaast data
		return new RainProbability(50, 10);
	}

	public List<Weather> getWeatherPercentage(Integer hours) {
		logger.info("Requesting Weather forecast for {} hours", hours);

		Forecast forecast = getLatestForecast();
		DateTime dateTimeTo = Utils.getCurrentDateTime().plusHours(hours);

		Map<Integer, WeatherWrapper> mapWeather = new HashMap<>();
		int totalCount = 0;
		for (Data data : forecast.getDataList()) {
			if (data.getWeathers() != null && new DateTime(data.getDt().getTime()).isBefore(dateTimeTo.toInstant())) {
				for (Weather weather : data.getWeathers()) {
					WeatherWrapper wrapper = mapWeather.get(weather.getId());
					if (wrapper == null) {
						wrapper = new WeatherWrapper(weather);
						mapWeather.put(weather.getId(), wrapper);
					}
					wrapper.count++;
					totalCount++;
				}
			}
		}

		//calculate the percentage
		List<Weather> result = new ArrayList<>();
		for (WeatherWrapper wr : mapWeather.values()) {
			float proportionCorrect = ((float) wr.count) / ((float) totalCount);
			wr.weather.setPercentage(Utils.roundFloat(proportionCorrect * 100, 2));
			result.add(wr.weather);
		}
		return result;
	}

	private class WeatherWrapper {

		public WeatherWrapper(final Weather weather) {
			this.weather = weather;
		}

		public Weather weather;

		public int count;
	}
}
