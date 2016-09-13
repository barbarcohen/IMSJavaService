package com.honeywell.ims.dao;

import org.springframework.stereotype.Repository;

import com.honeywell.ims.api.device.SensorData;

/**
 * Created by h134602 on 9/13/2016.
 */
@Repository
public class DeviceDao {

	private SensorData deviceData = null;

	public void saveDeviceData(SensorData deviceData){
		this.deviceData = deviceData;
	}

	public SensorData getDeviceData(final String id){
		return this.deviceData;
	}
}
