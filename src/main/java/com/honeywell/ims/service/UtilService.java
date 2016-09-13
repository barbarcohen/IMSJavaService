package com.honeywell.ims.service;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * Created by h134602 on 9/13/2016.
 */

public class UtilService {


	public static Date getCurrentDate() {
		return DateTime.now().toDate();
	}

	public static DateTime getCurrentDateTime() {
		return DateTime.now();
	}

}
