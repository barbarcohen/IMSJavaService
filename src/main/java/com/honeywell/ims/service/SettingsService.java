package com.honeywell.ims.service;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.dao.SettingsDao;

/**
 * Created by h134602 on 9/13/2016.
 */
@Component
public class SettingsService {

	@Autowired
	private SettingsDao settingDao;

	private Logger logger = LoggerFactory.getLogger(SettingsService.class);

	public void saveSettings(final Settings settings) {
		logger.info("Saving user settings {}", settings);
		settingDao.saveSettings(settings);
	}

	public Settings getSettings(final String id) {
		Settings userSettings = settingDao.getUserSettings(id);
		logger.info("Retrieving user settings {}", userSettings);

		return userSettings;
	}

	public Settings resetWateringTime(final String id){
		Settings settings = getSettings(id);
		DateTime newDate = new DateTime(settings.getNextWateringDate()).plusHours(settings.getDelay());
		settings.setNextWateringDate(newDate.toDate());
		saveSettings(settings);
		return settings;
	}

}
