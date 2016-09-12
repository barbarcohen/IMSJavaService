package com.honeywell.ims.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by h134602 on 9/12/2016.
 */
@Component
public class ScheduleService {

	private String latestData;

	private int i = 0;
	private static final int FETCH_RATE = 15000;

	@Scheduled(fixedRate = FETCH_RATE)
	public void fetchDeviceData(){
		latestData = Integer.valueOf(i++).toString();
	}

	public String getLatestData(){
		return latestData;
	}
}
