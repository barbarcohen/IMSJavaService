package com.honeywell.ims.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.honeywell.ims.Constants;
import com.honeywell.ims.api.device.SensorData;
import com.honeywell.ims.api.web.DeviceStatus;
import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.domain.RainProbability;
import com.honeywell.ims.domain.UserSettings;
import com.honeywell.ims.enums.Command;
import com.honeywell.ims.enums.WateringStatus;

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
	private IrrigationService irrigationService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private SettingsDao settingsDao;

	private Logger logger = LoggerFactory.getLogger(ScheduleService.class);

	@Scheduled(fixedRate = Constants.FORECAST_CHECK_RATE)
	public void refreshForecast() {
		logger.info("Fetching fresh forecast {}", UtilService.getCurrentDate());
		forecastService.fetchForecast();
	}

	@Scheduled(fixedRate = Constants.WATERING_CHECK_RATE)
	public void checkForWatering() {
		Date currentDate = UtilService.getCurrentDate();
		logger.info("Running check for watering, {}", currentDate);

		UserSettings userSettings = settingsDao.getUserSettings(null);

		//run only when date is immediate
		if (isTheRightTime(userSettings.getNextWatering(), currentDate)) {
			handleWatering(userSettings);
		}
	}

	private void handleWatering(UserSettings userSettings) {
		logger.info("Executing watering, with settings {}", userSettings);

		//get status
		DeviceStatus deviceStatus = wateringService.getWateringStatus();

		//get forecast data
		RainProbability rainProbability = forecastService.getRainProbability(userSettings);

		//get the humidity from sensor
		SensorData humidityDeviceData = deviceService.getCachedDeviceData(userSettings.getDeviceId());

		//compute the need for watering
		boolean irrigate = irrigationService.computeIrrigation(userSettings, rainProbability, humidityDeviceData);

		//water the plants
		if (irrigate && WateringStatus.RUNNING != deviceStatus.getStatus()) {
			logger.info("Automatically watering the plans");
			deviceService.runCommand(Command.on.getValue());
		}
	}

	/**
	 * Detects whether the waterring should run
	 *
	 * @return TRUE if watering shuiuld be started, FALSE otherwise
	 */
	private boolean isTheRightTime(final Date userWateringDate, final Date currentDate) {
		if (userWateringDate == null || currentDate == null) {
			logger.warn("UserDate is null, cant water");
			return false;
		}
		boolean result = userWateringDate.getTime() + Constants.WATERING_TIME_THRESHOLD > currentDate.getTime() || userWateringDate.getTime() <= currentDate.getTime();
		logger.info("Is the time for watering? {} (time remaining: {})", result, currentDate.getTime() - userWateringDate.getTime());
		return result;
	}
}
