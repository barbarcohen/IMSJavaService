package com.honeywell.ims.api.device;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.honeywell.ims.api.web.DeviceData;
import com.honeywell.ims.enums.WateringStatus;

/**
 * Created by h134602 on 9/13/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorData {

	@JsonProperty(value = "rows")
	List<SensorDataRows> rows;

	public List<SensorDataRows> getRows() {
		return rows;
	}

	public void setRows(final List<SensorDataRows> rows) {
		this.rows = rows;
	}

	public SensorDataDoc getFirst() {
		if (CollectionUtils.isNotEmpty(rows)) {
			return rows.get(0).getDocumentData();
		}
		return new SensorDataDoc();
	}

	//FIXME this should  be somewhere else
	public DeviceData convertToDeviceData() {
		SensorDataDoc sensorData = null;
		if (CollectionUtils.isNotEmpty(rows)) {
			sensorData = rows.get(0).getDocumentData();
		} else {
			sensorData = new SensorDataDoc();
		}
		DeviceData status = new DeviceData();
		status.setStatus("1".equals(sensorData.getIsRunning()) ? WateringStatus.RUNNING : WateringStatus.STOPPED);
		status.setHumidity(convertHumidity(sensorData.getHumidityOriginal()));
		return status;
	}


	private double convertHumidity(String humidityOriginal) {
		if (humidityOriginal != null) {
			try {
				return Double.valueOf(humidityOriginal).doubleValue();
			} catch (NumberFormatException e) {
			}
		}
		return 0;
	}

	@Override
	public String toString() {
		return "SensorData{" +
			"rows=" + rows +
			'}';
	}
}
