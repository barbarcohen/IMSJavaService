package com.honeywell.ims.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.honeywell.ims.api.weather.Forecast;
import com.honeywell.ims.api.web.Watering;
import com.honeywell.ims.dao.CommandEnum;
import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.domain.UserSettings;
import com.honeywell.ims.domain.WateringStatus;

/**
 * Created by h134602 on 9/12/2016.
 */
@Component
public class ScheduleService {

	@Autowired
	private WateringService wateringService;

	@Autowired
	private ForecastService forecastService;

	@Autowired
	private SettingsDao settingsDao;

	private Logger logger = LoggerFactory.getLogger(ScheduleService.class);

	private int TRESHOLD = 1 * 60 * 1000;//in miliseconds

	private static final int FETCH_RATE = 15 * 60 * 1000;//15 minutes

	@Scheduled(fixedRate = FETCH_RATE)
	public void checkForWatering() {

		Date currentDate = getCurrentDate();
		logger.info("Running check for watering, {}", currentDate);

		UserSettings userSettings = settingsDao.getUserSettings(null);

		//run only when date is immediate
		if (isTheRightTime(userSettings.getNextWatering(), currentDate)) {
			executeWatering(userSettings);
		}
	}

	private void executeWatering(UserSettings settings) {
		logger.info("Executing watering, {}", settings);

		//get forecast data
		Forecast forecast = forecastService.getActualForecast();

		//get status
		Watering watering = wateringService.getWateringStatus();

		//get the humidity from sensor

		//get the temperatur from sensor

		//compute the need for watering
		boolean needWatering = true;

		//water the plants
		if (needWatering && WateringStatus.RUNNING.name() != watering.getStatus()) {
			logger.info("Automatically watering the plans");
			wateringService.runCommand(CommandEnum.on.getValue());
		}
	}

	public Date getCurrentDate() {
		return new Date();
	}

	/**
	 * Detects whether the waterring should run
	 *
	 * @return TRUE if watering shuiuld be started, FALSE otherwise
	 */
	private boolean isTheRightTime(final Date userWateringDate, final Date currentDate) {
		if (userWateringDate == null || currentDate == null) {
			return false;
		}
		return userWateringDate.getTime() + TRESHOLD > currentDate.getTime() && userWateringDate.getTime() <= currentDate.getTime();
	}
}
