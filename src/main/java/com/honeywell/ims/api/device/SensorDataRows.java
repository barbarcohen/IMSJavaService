package com.honeywell.ims.api.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/13/2016.
 */
public class SensorDataRows {

	@JsonProperty(value = "doc")
	private SensorDataDoc documentData;

	public SensorDataDoc getDocumentData() {
		return documentData;
	}

	public void setDocumentData(final SensorDataDoc documentData) {
		this.documentData = documentData;
	}
}
