package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.honeywell.ims.Constants;
import com.honeywell.ims.api.device.SensorData;
import com.honeywell.ims.api.device.WateringCommand;
import com.honeywell.ims.api.web.DeviceStatus;
import com.honeywell.ims.dao.DeviceDao;
import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.domain.UserSettings;
import com.honeywell.ims.enums.Command;
import com.honeywell.ims.enums.WateringStatus;

/**
 * Created by h134602 on 9/13/2016.
 */
@Component
public class DeviceService {

	private Logger logger = LoggerFactory.getLogger(DeviceService.class);

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private WateringService wateringService;

	@Autowired
	private SettingsDao settingsDao;

	public DeviceStatus getStatus(String deviceId){
		SensorData sensorData = getCachedDeviceData(null);
		return sensorData.convertToDeviceStatus();
	}

	public SensorData getFreshDeviceData(String id){
		logger.info("Requesting fresh device data {}", id);
		RestTemplate restTemplate = new RestTemplate();
		SensorData deviceData = restTemplate.getForObject(Constants.CLOUDANT_SENSOR_DATA_URL, SensorData.class);

		return deviceData;
	}

	public SensorData getCachedDeviceData(String id){
		logger.info("Requesting cached device data ({})", id);
		SensorData deviceData = deviceDao.getDeviceData(id);
		if(deviceData == null){
			logger.info("Device not cached yet. Going for fresh one...", id);
			deviceData = getFreshDeviceData(id);
		}
		return deviceData;
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
				return wateringService.startWatering(new WateringCommand(settings.getWateringDuration()));
			case off:
				logger.info("Executing OFF command");

				return wateringService.stopWatering(new WateringCommand());
			default:
				break;
		}
		return false;
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
