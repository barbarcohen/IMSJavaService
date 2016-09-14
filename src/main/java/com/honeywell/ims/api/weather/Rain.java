package com.honeywell.ims.api.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Rain {

	@JsonProperty(value="3h")
	private double downfall = 0;

	public double getDownfall() {
		return downfall;
	}

	public void setDownfall(final double downfall) {
		this.downfall = downfall;
	}
}
