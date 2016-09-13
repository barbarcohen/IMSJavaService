package com.honeywell.ims.dao;

import org.springframework.stereotype.Repository;

import com.honeywell.ims.api.weather.Forecast;

/**
 * Created by h134602 on 9/13/2016.
 */
@Repository
public class ForecastDao {

	private Forecast forecast;

	public void saveForecast(Forecast forecast){
		this.forecast = forecast;
	}

	public Forecast getLatestForecast(){
		return forecast;
	}
}
