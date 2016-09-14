package com.honeywell.ims.service;

import com.honeywell.ims.enums.WateringStatus;
import org.joda.time.LocalDateTime;
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
        if (deviceData.getStatus().getValue().equals(WateringStatus.STOPPED.getValue())) {
            if (deviceData.getHumidity() < settings.getMinHumidityThreshold()) {
                if (rainProbability.getRainProbability() < 50) {
                    return true;
                } else {
                    logger.info("skiping watering because rain probability is high");
                }
            } else {
                logger.info("skiping watering because humidity is high enough");
            }
        } else {
            logger.info("skiping watering because watering is running");
        }


        return false;
    }
}
