package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.honeywell.ims.api.device.SensorData;
import com.honeywell.ims.domain.RainProbability;
import com.honeywell.ims.domain.UserSettings;

/**
 * Created by h134602 on 9/13/2016.
 */
@Component
public class IrrigationService {

	private Logger logger = LoggerFactory.getLogger(IrrigationService.class);

	public boolean computeIrrigation(UserSettings settings, RainProbability rainProbability, SensorData humidityData) {
		logger.info("Computing irrigation");
		if (settings.isForceIrrigation()) {
			logger.info("Irrigation is forced!");
			return true;
		}
		//TODO compute irigation here
		return false;
	}
}
