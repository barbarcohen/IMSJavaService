package com.honeywell.ims.service;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.dao.SettingsDao;
import com.honeywell.ims.domain.UserSettings;

/**
 * Created by h134602 on 9/13/2016.
 */
@Component
public class SettingsService {

	@Autowired
	private SettingsDao settingDao;

	@Autowired
	private Mapper mapper;

	private Logger logger = LoggerFactory.getLogger(SettingsService.class);

	public void saveSettings(final Settings settings) {
		UserSettings userSettings = mapper.map(settings, UserSettings.class);
		logger.info("Saving user settings {}", userSettings);
		settingDao.saveSettings(userSettings);
	}

	public Settings getSettings(final String id) {
		UserSettings userSettings = settingDao.getUserSettings(id);
		logger.info("Retrieving user settings {}", userSettings);

		Settings settings = mapper.map(userSettings, Settings.class);
		return settings;
	}

}
