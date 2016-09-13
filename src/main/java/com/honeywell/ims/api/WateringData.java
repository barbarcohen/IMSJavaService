package com.honeywell.ims.api;

import com.honeywell.ims.api.web.DeviceData;
import com.honeywell.ims.api.web.Settings;

/**
 * Created by h134602 on 9/13/2016.
 */
public class WateringData {

	private Settings settings;

	private DeviceData deviceData;

	public static WateringData create(Settings settings, DeviceData deviceData){
		WateringData wateringData = new WateringData();
		wateringData.setDeviceData(deviceData);
		wateringData.setSettings(settings);
		return wateringData;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(final Settings settings) {
		this.settings = settings;
	}

	public DeviceData getDeviceData() {
		return deviceData;
	}

	public void setDeviceData(final DeviceData deviceData) {
		this.deviceData = deviceData;
	}
}
