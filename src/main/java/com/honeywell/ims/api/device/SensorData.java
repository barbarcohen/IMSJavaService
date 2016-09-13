package com.honeywell.ims.api.device;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/13/2016.
 */
public class SensorData {

	@JsonProperty(value = "rows.docs")
	List<SensorDataRows> rows;

	public List<SensorDataRows> getRows() {
		return rows;
	}

	public void setRows(final List<SensorDataRows> rows) {
		this.rows = rows;
	}
}
