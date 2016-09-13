package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.honeywell.ims.api.web.DeviceData;
import com.honeywell.ims.api.web.Settings;
import com.honeywell.ims.domain.RainProbability;

/**
 * Created by h134602 on 9/13/2016.
 */
@Component
public class IrrigationService {

	private Logger logger = LoggerFactory.getLogger(IrrigationService.class);

	public boolean computeIrrigation(Settings settings, RainProbability rainProbability, DeviceData deviceData) {
		logger.info("Computing irrigation with settings {}, Probability {} and SensorData {}", new Object[]{settings, rainProbability, deviceData});
		if (settings.isForceIrrigation()) {
			logger.info("Irrigation is forced!");
			return true;
		}
		//TODO compute irrigation here
		//get next watering time -> how much time is left to watering
		//get next watering amount -> in milimeters?
		//get rain probability
		//get rain downfall amount
		//get sensor humidity

		//do we need to watering
		//are we watering right now?
		//-if yes, how much will it rain in the future(? what is the future, how to measure the rain)

		//- if no, we need to
		return false;
	}
}
