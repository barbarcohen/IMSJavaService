package com.honeywell.ims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.honeywell.ims.Constants;
import com.honeywell.ims.dao.DeviceDao;
import com.honeywell.ims.domain.DeviceData;

/**
 * Created by h134602 on 9/13/2016.
 */
@Component
public class DeviceService {

	private Logger logger = LoggerFactory.getLogger(DeviceService.class);

	@Autowired
	private DeviceDao deviceDao;

	public DeviceData getFreshDeviceData(String id){
		logger.info("Requesting fresh device data {id}", id);
		RestTemplate restTemplate = new RestTemplate();
		DeviceData deviceData = restTemplate.getForObject(Constants.CLOUDANT_SENSOR_DATA_URL, DeviceData.class);
		return deviceData;
	}

	public DeviceData getCachedDeviceData(String id){
		logger.info("Requesting cached device data ({})", id);
		DeviceData deviceData = deviceDao.getDeviceData(id);
		if(deviceData == null){
			logger.info("Device not cached yet. Going for fresh one...", id);
			deviceData = getFreshDeviceData(id);
		}
		return deviceData;
	}
}
