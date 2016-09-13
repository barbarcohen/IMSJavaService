package com.honeywell.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.honeywell.ims.api.web.Command;
import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.api.web.Watering;
import com.honeywell.ims.service.ScheduleService;
import com.honeywell.ims.service.SettingsService;
import com.honeywell.ims.service.WateringService;

/**
 * Created by h134602 on 9/12/2016. API for providing end user data (WEB or mobile app)
 */
@RestController()
@RequestMapping("/control")
public class WebResource {

	@Autowired
	private WateringService wateringService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private SettingsService settingsService;

	@RequestMapping("/status")
	public Watering getStatusData() {
		return wateringService.getWateringStatus();
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
		boolean isSuccess = wateringService.runCommand(cmd);
		return Command.result(cmd, isSuccess);
	}


}
