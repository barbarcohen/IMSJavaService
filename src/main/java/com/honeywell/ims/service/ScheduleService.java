package com.honeywell.ims.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.honeywell.ims.Constants;
import com.honeywell.ims.api.web.DeviceData;
import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.domain.RainProbability;
import com.honeywell.ims.enums.Command;
import com.honeywell.ims.enums.WateringStatus;

/**
 * Created by h134602 on 9/12/2016.
 */
@Component
public class ScheduleService {

	@Autowired
	private ForecastService forecastService;

	@Autowired
	private IrrigationService irrigationService;

	@Autowired
	private SettingsService settingsService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private SettingsDao settingsDao;

	private Logger logger = LoggerFactory.getLogger(ScheduleService.class);

	@Scheduled(fixedRate = Constants.FORECAST_CHECK_RATE)
	public void refreshForecast() {
		logger.info("Fetching fresh forecast {}", Utils.getCurrentDate());
		forecastService.fetchForecast();
	}

	@Scheduled(fixedRate = Constants.DEVICE_CHECK_RATE)
	public void refreshDevice() {
		logger.info("Fetching fresh device {}", Utils.getCurrentDate());
		deviceService.fetchDevice(null);
	}

	@Scheduled(fixedRate = Constants.WATERING_CHECK_RATE)
	public void checkForWatering() {
		Date currentDate = Utils.getCurrentDate();
		logger.info("Running check for watering, {}", currentDate);

		Settings userSettings = settingsDao.getUserSettings(null);

		//run only when date is immediate
		if (isTheRightTime(userSettings.getNextWateringDate(), currentDate)) {
			handleWatering(userSettings);
		}
	}

	private void handleWatering(Settings userSettings) {
		logger.info("Executing watering, with settings {}", userSettings);

		//get status
		DeviceData deviceData = deviceService.getDeviceData(null);

		//get forecast data
		RainProbability rainProbability = forecastService.getRainProbability(userSettings);

		//compute the need for watering
		boolean irrigate = irrigationService.computeIrrigation(userSettings, rainProbability, deviceData);

		//water the plants
		if (irrigate && WateringStatus.RUNNING != deviceData.getStatus()) {
			logger.info("Automatically start the plants");
			deviceService.runCommand(Command.on.getValue());
		} else {
			logger.info("No irrigation needed");
			if(WateringStatus.RUNNING == deviceData.getStatus()){
				logger.info("Automatically STOP watering the plants");
				deviceService.runCommand(Command.off.getValue());
			}
		}
		settingsService.resetWateringTime(null);

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
		//FIXME
		boolean result = userWateringDate.getTime() + Constants.WATERING_TIME_THRESHOLD > currentDate.getTime() || userWateringDate.getTime() <= currentDate.getTime();
		logger.info("Is the time for watering? {} (time remaining: {})", result, currentDate.getTime() - userWateringDate.getTime());
		return result;
	}
}
