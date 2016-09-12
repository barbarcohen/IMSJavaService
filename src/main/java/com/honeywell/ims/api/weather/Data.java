package com.honeywell.ims.api.weather;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/12/2016.
 */
public class Data {

	private Date dt;

	@JsonProperty(value = "main")
	private MainData mainData;

	public Date getDt() {
		return dt;
	}

	public void setDt(final Date dt) {
		this.dt = dt;
	}

	public MainData getMainData() {
		return mainData;
	}

	public void setMainData(final MainData mainData) {
		this.mainData = mainData;
	}
}
