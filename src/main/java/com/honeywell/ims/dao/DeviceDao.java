package com.honeywell.ims.dao;

import org.springframework.stereotype.Repository;

import com.honeywell.ims.api.web.DeviceData;

/**
 * Created by h134602 on 9/13/2016.
 */
@Repository
public class DeviceDao {

	private DeviceData deviceData = null;

	public void saveDeviceData(DeviceData deviceData) {
		this.deviceData = deviceData;
	}

	public DeviceData getDeviceData(final String id) {
		return this.deviceData;
	}
}
