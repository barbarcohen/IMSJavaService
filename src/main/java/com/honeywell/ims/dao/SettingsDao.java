package com.honeywell.ims.dao;

import org.springframework.stereotype.Repository;

import com.honeywell.ims.domain.UserSettings;

/**
 * Created by h134602 on 9/13/2016.
 */
@Repository
public class SettingsDao {

	//simullate database
	private UserSettings userSettings = UserSettings.createDefault();

	public void saveSettings(final UserSettings settings) {
		this.userSettings = settings;
	}

	public UserSettings getUserSettings(final String id){
		return userSettings;
	}
}
