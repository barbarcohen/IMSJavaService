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
import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.domain.RainProbability;

@Component
public class ForecastService {

	private Logger logger = LoggerFactory.getLogger(ForecastService.class);

	@Autowired
	private ForecastDao forecastDao;

	@Autowired
	private SettingsDao settingsDao;

	private String createForecastUrl() {
		Settings settings = settingsDao.getUserSettings(null);

		String url = Constants.FORECAST_URL + settings.getForecastLocationId() + "&APPID=" + Constants.FORECAST_API_KEY;
		return url;
	}

	public Forecast fetchForecast() {
		String url = createForecastUrl();
		logger.info("Requesting fresh forecast from URL: {} ", url);
		RestTemplate restTemplate = new RestTemplate();
		Forecast forecast = restTemplate.getForObject(url, Forecast.class);
		forecastDao.saveForecast(forecast);

		return forecast;
	}

	public Forecast getLatestForecast() {
		Forecast forecast = forecastDao.getLatestForecast();
		if (forecast == null) {
			forecast = fetchForecast();
		}
		return forecast;
	}

	/**
	 * @return TRUE if should rain, FALSE otherwise
	 */
	public RainProbability getRainProbability(Settings settings) {
		Forecast forecast = getLatestForecast();
		int deleyValueInDays = 1;
		DateTime wateringWithDealy = new DateTime(settings.getNextWateringDate()).plusDays(deleyValueInDays);
		double df = forecast.getDataList().stream()
			.filter(x -> new DateTime(x.getDt().getTime()).isBefore(wateringWithDealy))
			.mapToDouble(x -> x.getRain().getDownfall()).sum();

		RainProbability rp = null;

		if (df > 1) {
			rp = new RainProbability(100, df);
		} else {
			rp = new RainProbability(0, df);
		}

		logger.info("rp prob: " + rp.getRainProbability() + " df: " + rp.getDownfall());

		return rp;
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
