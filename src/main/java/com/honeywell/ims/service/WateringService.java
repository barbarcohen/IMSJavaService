package com.honeywell.ims.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.honeywell.ims.api.web.Watering;

/**
 * Created by h134602 on 9/12/2016.
 */
@Service
public class WateringService {

	public Watering getStatus(){
		Watering watering = new Watering();
		watering.setStatus("running");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 4);
		watering.setNextWateringDate(calendar.getTime());
		return watering;
	}

}
