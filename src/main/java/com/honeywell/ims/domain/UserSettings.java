package com.honeywell.ims.domain;

import java.util.Date;

/**
 * Created by h134602 on 9/13/2016.
 */
public class UserSettings {

	private String id;

	private Date nextWatering;

	private long wateringDuration;

	public static UserSettings createDefault() {
		return new UserSettings(null, null, 0);
	}

	public UserSettings() {
	}

	public UserSettings(final String id, final Date nextWatering, final long wateringDuration) {
		this.nextWatering = nextWatering;
		this.id = id;
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

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserSettings{" +
			"id='" + id + '\'' +
			", nextWatering=" + nextWatering +
			", wateringDuration=" + wateringDuration +
			'}';
	}
}
