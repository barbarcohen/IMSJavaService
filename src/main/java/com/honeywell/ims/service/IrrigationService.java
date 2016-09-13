package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.honeywell.ims.domain.RainProbability;
import com.honeywell.ims.domain.DeviceData;

/**
 * Created by h134602 on 9/13/2016.
 */
@Component
public class IrrigationService {

	private Logger logger = LoggerFactory.getLogger(IrrigationService.class);

	public boolean computeIrrigation(RainProbability rainProbability, DeviceData hunidityData) {
		logger.debug("Computing irrigation");
		//TODO compute irigation here
		return true;
	}
}
