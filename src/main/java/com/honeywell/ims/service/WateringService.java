package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.honeywell.ims.Constants;
import com.honeywell.ims.api.device.WateringCommand;
import com.honeywell.ims.api.web.Watering;
import com.honeywell.ims.enums.Command;
import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.dao.WateringDao;
import com.honeywell.ims.domain.UserSettings;
import com.honeywell.ims.enums.WateringStatus;

/**
 * Created by h134602 on 9/12/2016.
 */
@Service
public class WateringService {

	private Logger logger = LoggerFactory.getLogger(WateringService.class);

	@Autowired
	private SettingsDao settingsDao;

	@Autowired
	private WateringDao wateringDao;

	public Watering getWateringStatus() {
		Watering watering = new Watering();
		UserSettings userSettings = settingsDao.getUserSettings(null);
		watering.setNextWateringDate(userSettings.getNextWatering());
		watering.setStatus(wateringDao.getStatus());

		logger.info("Requesting watering status: {} ", watering);

		return watering;
	}

	/**
	 * @return true if command was executed correctly, FALSE otherwise
	 */
	public boolean runCommand(final String commandValue) {
		Command command = findCommandForKey(commandValue);
		if (command == null) {
			logger.info("Command does not exists! Nothing executed.");
			return false;
		}

		switch (command) {
			case on:
				logger.info("Executing ON command");
				//get the settings
				UserSettings settings = settingsDao.getUserSettings(null);
				return startWatering(new WateringCommand(settings.getWateringDuration()));
			case off:
				logger.info("Executing OFF command");

				return stopWatering(new WateringCommand());
			default:
				break;
		}
		return false;
	}

	/**
	 * Request DEVICE to START watering
	 *
	 * @return true if was accepted, false otherwise
	 */
	public boolean startWatering(WateringCommand wateringCommand) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity responseEntity = restTemplate.postForEntity(Constants.WATERING_START_URL, wateringCommand, null);

		logger.info("Request start watering {} for data {} with result {}", new Object[]{Constants.WATERING_START_URL, wateringCommand, responseEntity.getStatusCode()});
		boolean isSuccess = HttpStatus.OK == responseEntity.getStatusCode();
		if (isSuccess) {
			//update the status
			wateringDao.saveStatus(WateringStatus.RUNNING);
		}
		return isSuccess;
	}

	/**
	 * Request DEVICE to STOP watering
	 *
	 * @return true if was accepted, false otherwise
	 */
	public boolean stopWatering(WateringCommand wateringCommand) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity responseEntity = restTemplate.postForEntity(Constants.WATERING_STOP_URL, wateringCommand, null);

		logger.info("Request stop watering {} for data {} with result {}", new Object[]{Constants.WATERING_STOP_URL, wateringCommand, responseEntity.getStatusCode()});
		boolean isSuccess = HttpStatus.OK == responseEntity.getStatusCode();
		if (isSuccess) {
			//update the status
			wateringDao.saveStatus(WateringStatus.STOPPED);
		}
		return isSuccess;
	}

	private Command findCommandForKey(String value) {
		try {
			Command commandEnum = Command.valueOf(value);
			return commandEnum;
		} catch (IllegalArgumentException e) {
			logger.error("Unknown command: {}", value);
		}
		return null;
	}

}
