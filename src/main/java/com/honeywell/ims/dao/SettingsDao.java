package com.honeywell.ims.dao;

import org.springframework.stereotype.Repository;

import com.honeywell.ims.api.web.Settings;

/**
 * Created by h134602 on 9/13/2016.
 */
@Repository
public class SettingsDao {

	private Settings userSettings = Settings.createDefault();

	public void saveSettings(final Settings settings) {
		this.userSettings = settings;
	}

	public Settings getUserSettings(final String id){
		return userSettings;
	}
}
