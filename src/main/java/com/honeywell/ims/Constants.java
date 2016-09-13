package com.honeywell.ims;

/**
 * Created by h134602 on 9/13/2016.
 */
public interface Constants {

	String WATERING_STOP_URL = "http://imsred.mybluemix.net/public/command/waterstart";
	String WATERING_START_URL = "http://imsred.mybluemix.net/public/command/waterstop";
	String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?id=3078610&APPID="+Constants.FORECAST_API_KEY;
	String FORECAST_API_KEY = "76689c24d33b982520e19e323e4f8add";

	String CLOUDANT_SENSOR_DATA_URL = "https://dec32ea6-0338-43b8-98ba-9fc053d538b1-bluemix.cloudant.com/sensordata/_all_docs?include_docs=true&limit=1&descending=true";

	int WATERING_CHECK_RATE = 1 * 60 * 1000;//1 minutes

	int WATERING_TIME_THRESHOLD = 1 * 60 * 1000;//1 minute

	int FORECAST_CHECK_RATE = 1 * 60 * 1000;//1
}