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

	private Clouds clouds;

	private Rain rain = new Rain();

	@JsonProperty(value = "dt_txt")
	private String dateText;

	public String getDateText() {
		return dateText;
	}

	public void setDateText(final String dateText) {
		this.dateText = dateText;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(final Clouds clouds) {
		this.clouds = clouds;
	}

	public Rain getRain() {
		return rain;
	}

	public void setRain(final Rain rain) {
		this.rain = rain;
	}

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
