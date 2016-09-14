package com.honeywell.ims.api.weather;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.honeywell.ims.service.JsonDateSerializer;

/**
 * Created by h134602 on 9/12/2016.
 */
public class Data {

	private Date dt;

	@JsonProperty(value = "main")
	private MainData mainData;

	private Clouds clouds;

	private Wind wind;

	private List<Weather> weather;

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

	@JsonDeserialize(using = JsonDateSerializer.class)
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

	public Wind getWind() {
		return wind;
	}

	public void setWind(final Wind wind) {
		this.wind = wind;
	}

	public List<Weather> getWeathers() {
		return weather;
	}

	public void setWeather(final List<Weather> weather) {
		this.weather = weather;
	}
}
