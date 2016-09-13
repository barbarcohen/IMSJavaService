package com.honeywell.ims.domain;

import java.util.Date;

/**
 * Created by h134602 on 9/13/2016.
 */
public class UserSettings {

	private String deviceId;

	private Date nextWatering;

	private long wateringDuration;

	private int minHumidityThreshold;

	public static UserSettings createDefault() {
		return new UserSettings(null, null, 0);
	}

	public UserSettings() {
	}

	public UserSettings(final String deviceId, final Date nextWatering, final long wateringDuration) {
		this.nextWatering = nextWatering;
		this.deviceId = deviceId;
		this.wateringDuration = wateringDuration;
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}

	public int getMinHumidityThreshold() {
		return minHumidityThreshold;
	}

	public void setMinHumidityThreshold(final int minHumidityThreshold) {
		this.minHumidityThreshold = minHumidityThreshold;
	}

	@Override
	public String toString() {
		return "UserSettings{" +
			"deviceId='" + deviceId + '\'' +
			", nextWatering=" + nextWatering +
			", wateringDuration=" + wateringDuration +
			", minHumidityThreshold=" + minHumidityThreshold +
			'}';
	}
}
