package com.honeywell.ims.api.device;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.honeywell.ims.api.web.DeviceStatus;
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

	public DeviceStatus convertToDeviceStatus() {
		SensorDataDoc sensorData = null;
		if (CollectionUtils.isNotEmpty(rows)) {
			sensorData = rows.get(0).getDocumentData();
		}
		sensorData = new SensorDataDoc();
		DeviceStatus status = new DeviceStatus();
		status.setStatus(sensorData.isRunning() ? WateringStatus.RUNNING : WateringStatus.STOPPED);
		status.setHumidity(sensorData.getHumidity());
		return status;
	}

}
