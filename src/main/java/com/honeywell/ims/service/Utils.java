package com.honeywell.ims.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * Created by h134602 on 9/13/2016.
 */

public class Utils {

	private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");

	public static Date getCurrentDate() {
		return DateTime.now().toDate();
	}

	public static DateTime getCurrentDateTime() {
		return DateTime.now();
	}

	public double celvinToCelsius(double celvin) {
		return celvin - 273.16;
	}

	public static double roundFloat(float origin, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(origin));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

}
