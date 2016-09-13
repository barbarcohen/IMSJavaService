package com.honeywell.ims.api.web;

import java.util.Date;
import java.util.Set;

import org.joda.time.DateTime;

import com.honeywell.ims.service.UtilService;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Settings {

	private String deviceId;

	//date
	private Date nextWatering;

	//seconds
	private int repeatEvery;

	//seconds
	private long wateringDuration;

	private int minHumidityThreshold;

	private boolean forceIrrigation;

	public static Settings createDefault(){
		Settings settings = new Settings();
		settings.setNextWatering(UtilService.getCurrentDateTime().plusHours(1).toDate());
		settings.setRepeatEvery(1);
		settings.setWateringDuration(5);
		settings.setMinHumidityThreshold(10);
		return settings;
	}
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getNextWatering() {
		return nextWatering;
	}

	public void setNextWatering(final Date nextWatering) {
		this.nextWatering = nextWatering;
	}

	public long getWateringDuration() {
		return wateringDuration;
	}

	public void setWateringDuration(final long wateringDuration) {
		this.wateringDuration = wateringDuration;
	}

	public int getMinHumidityThreshold() {
		return minHumidityThreshold;
	}

	public void setMinHumidityThreshold(final int minHumidityThreshold) {
		this.minHumidityThreshold = minHumidityThreshold;
	}

	public boolean isForceIrrigation() {
		return forceIrrigation;
	}

	public void setForceIrrigation(final boolean forceIrrigation) {
		this.forceIrrigation = forceIrrigation;
	}

	public int getRepeatEvery() {
		return repeatEvery;
	}

	public void setRepeatEvery(final int repeatEvery) {
		this.repeatEvery = repeatEvery;
	}

	@Override
	public String toString() {
		return "Settings{" +
			"deviceId='" + deviceId + '\'' +
			", nextWatering=" + nextWatering +
			", repeatEvery=" + repeatEvery +
			", wateringDuration=" + wateringDuration +
			", minHumidityThreshold=" + minHumidityThreshold +
			", forceIrrigation=" + forceIrrigation +
			'}';
	}
}
