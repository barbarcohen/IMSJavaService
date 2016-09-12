package com.honeywell.ims.api.weather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

	private City city;

	private String country;

	private String message;

	@JsonProperty(value = "list")
	private List<Data> dataMap;

	public List<Data> getDataMap() {
		return dataMap;
	}

	public void setDataMap(final List<Data> dataMap) {
		this.dataMap = dataMap;
	}

	public City getCity() {
		return city;
	}

	public void setCity(final City city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
