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

	@JsonProperty(value = "cnt")
	private int count;

	@JsonProperty(value = "list")
	private List<Data> dataList;

	public List<Data> getDataList() {
		return dataList;
	}

	public void setDataList(final List<Data> dataList) {
		this.dataList = dataList;
	}

	public City getCity() {
		return city;
	}

	public void setCity(final City city) {
		this.city = city;
	}

	public int getCount() {
		return count;
	}

	public void setCount(final int count) {
		this.count = count;
	}
}
