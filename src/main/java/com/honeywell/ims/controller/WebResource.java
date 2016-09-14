package com.honeywell.ims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.honeywell.ims.api.WateringData;
import com.honeywell.ims.api.web.Command;
import com.honeywell.ims.api.web.DeviceData;
import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.service.DeviceService;
import com.honeywell.ims.service.ScheduleService;
import com.honeywell.ims.service.SettingsService;

/**
 * Created by h134602 on 9/12/2016. API for providing end user data (WEB or mobile app)
 */
@RestController()
@RequestMapping("/control")
public class WebResource {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private SettingsService settingsService;

	@Autowired
	private DeviceService deviceService;

	private Logger logger = LoggerFactory.getLogger(WebResource.class);

	@RequestMapping("/status")
	public WateringData getStatusData() {
		logger.info("Requesting watering status: {} ");
		DeviceData deviceData = deviceService.getDeviceData(null);
		Settings settings = settingsService.getSettings(null);
		return WateringData.create(settings, deviceData);
	}

	@RequestMapping(value = "/settings", method = RequestMethod.POST)
	public void saveSettings(@RequestBody Settings settings) {
		settingsService.saveSettings(settings);
	}

	@RequestMapping(value = "/settings")
	public Settings getSettings() {
		return settingsService.getSettings(null);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{cmd}")
	public Command command(@PathVariable(value = "cmd") String cmd) {
		boolean isSuccess = deviceService.runCommand(cmd);
		return Command.result(cmd, isSuccess);
	}

	@RequestMapping("/dummy")
	public void dummyWattering() {
		scheduleService.checkForWatering();
	}

}
