package com.honeywell.ims.api.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Clouds {

	@JsonProperty(value = "all")
	private int cloudiness;

	public int getCloudiness() {
		return cloudiness;
	}

	public void setCloudiness(final int cloudiness) {
		this.cloudiness = cloudiness;
	}
}
