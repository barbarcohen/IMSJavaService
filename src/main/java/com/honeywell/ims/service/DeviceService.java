package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.honeywell.ims.Constants;
import com.honeywell.ims.api.device.SensorData;
import com.honeywell.ims.api.device.WateringCommand;
import com.honeywell.ims.api.web.DeviceData;
import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.dao.DeviceDao;
import com.honeywell.ims.dao.SettingsDao;
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
	private SettingsService settingsService;

	public DeviceData getStatus(String deviceId){
		DeviceData deviceData = getDeviceData(deviceId);
		return deviceData;
	}

	public DeviceData fetchDevice(String id){
		RestTemplate restTemplate = new RestTemplate();
		SensorData sensorData = restTemplate.getForObject(Constants.CLOUDANT_SENSOR_DATA_URL, SensorData.class);

		DeviceData deviceData = sensorData.convertToDeviceData();
		deviceDao.saveDeviceData(deviceData);

		//logger.info("Requesting fresh device data {}", deviceData);

		return deviceData;
	}

	public DeviceData getDeviceData(String id){
		logger.info("Requesting cached device data ({})", id);
		DeviceData deviceData = deviceDao.getDeviceData(id);
		if(deviceData == null){
			deviceData = fetchDevice(id);
			logger.info("Device not cached yet. Going for fresh one... {}", deviceData);
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
				return startWatering();
			case off:
				logger.info("Executing OFF command");

				return stopWatering();
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
	private boolean startWatering() {
		//get the settings
		Settings settings = settingsService.getSettings(null);
		DeviceData deviceData = deviceDao.getDeviceData(null);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity responseEntity = restTemplate.postForEntity(Constants.WATERING_START_URL, WateringCommand.create(settings.getWateringDuration()), null);

		logger.info("Request start watering {} for data {} with result {}", new Object[]{Constants.WATERING_START_URL, settings.getWateringDuration(), responseEntity.getStatusCode()});
		boolean isSuccess = HttpStatus.OK == responseEntity.getStatusCode();
		if (isSuccess) {
			//update the status
			deviceData.setStatus(WateringStatus.RUNNING);
			deviceDao.saveDeviceData(deviceData);
		}
		return isSuccess;
	}

	/**
	 * Request DEVICE to STOP watering
	 *
	 * @return true if was accepted, false otherwise
	 */
	private boolean stopWatering() {
		//get the settings
		Settings settings = settingsService.getSettings(null);
		DeviceData deviceData = deviceDao.getDeviceData(null);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity responseEntity = restTemplate.postForEntity(Constants.WATERING_STOP_URL, null, null);

		logger.info("Request stop watering {} for data {} with result {}", new Object[]{Constants.WATERING_STOP_URL, responseEntity.getStatusCode()});
		boolean isSuccess = HttpStatus.OK == responseEntity.getStatusCode();
		if (isSuccess) {
			//update the status
			deviceData.setStatus(WateringStatus.STOPPED);
			deviceDao.saveDeviceData(deviceData);
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
