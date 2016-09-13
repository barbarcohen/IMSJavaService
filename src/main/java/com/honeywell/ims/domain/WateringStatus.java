package com.honeywell.ims.domain;

/**
 * Created by h134602 on 9/13/2016.
 */
public enum WateringStatus {

	RUNNING("running"),
	STOPPED("stopped");

	private String value;

	private WateringStatus(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
