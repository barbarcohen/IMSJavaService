package com.honeywell.ims.service;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honeywell.ims.api.web.Watering;
import com.honeywell.ims.dao.CommandEnum;
import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.domain.UserSettings;

/**
 * Created by h134602 on 9/12/2016.
 */
@Service
public class WateringService {

	private Logger logger = LoggerFactory.getLogger(WateringService.class);

	@Autowired
	private SettingsDao settingsDao;

	public Watering getWateringStatus() {
		Watering watering = new Watering();
		watering.setStatus("running");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 4);
		watering.setNextWateringDate(calendar.getTime());
		return watering;
	}

	/**
	 * @return true if command was executed correctly, FALSE otherwise
	 */
	public boolean runCommand(final String commandValue) {
		CommandEnum command = findCommandForKey(commandValue);
		if(command == null){
			logger.info("Command does not exists! Nothing executed.");
			return false;
		}

		switch (command) {
			case on:
				logger.info("Executing ON command");
				//get the settings
				UserSettings settings = settingsDao.getUserSettings(null);
				//update the status
				return true;

			case off:
				logger.info("Executing OFF command");
				//update the status
				return true;
			default:
				break;
		}
		return false;
	}

	private CommandEnum findCommandForKey(String value) {
		try {
			CommandEnum commandEnum = CommandEnum.valueOf(value);
			return commandEnum;
		} catch (IllegalArgumentException e) {
			logger.error("Unknown command: {}", value);
		}
		return null;
	}

}
