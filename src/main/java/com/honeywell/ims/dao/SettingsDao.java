package com.honeywell.ims.dao;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.service.Utils;

/**
 * Created by h134602 on 9/13/2016.
 */
@Repository
public class SettingsDao {

	private Settings userSettings = Settings.createDefault();

	public void saveSettings(final Settings settings) {
		if(settings.getNextWaterigSeconds() > 0){
			settings.setNextWateringDate(Utils.getCurrentDateTime().plusSeconds(settings.getNextWaterigSeconds()).toDate());
			settings.setNextWaterigSeconds(0);
		}
		this.userSettings = settings;
	}

	public Settings getUserSettings(final String id){
		return userSettings;
	}
}
