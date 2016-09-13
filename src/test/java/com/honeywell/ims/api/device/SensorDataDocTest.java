package com.honeywell.ims.api.device;

import java.util.Arrays;

import org.junit.Test;

import com.honeywell.ims.api.web.DeviceData;

import junit.framework.Assert;

/**
 * Created by h134602 on 9/13/2016.
 */
public class SensorDataDocTest {

	@Test
	public void testGetHumidity() throws Exception {
		SensorDataDoc doc = new SensorDataDoc();
		doc.setHumidityOriginal("Humidity:67.8%; Relay:0%;, \r\n\n");
		SensorDataRows row = new SensorDataRows();
		row.setDocumentData(doc);
		SensorData data = new SensorData();
		data.setRows(Arrays.asList(row));

		DeviceData deviceData = data.convertToDeviceData();
		Assert.assertEquals(67.8, deviceData.getHumidity());
	}
}