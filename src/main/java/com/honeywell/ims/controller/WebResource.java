package com.honeywell.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honeywell.ims.api.web.Watering;
import com.honeywell.ims.service.WateringService;

/**
 * Created by h134602 on 9/12/2016.
 */
@RestController()
@RequestMapping("/control")
public class WebResource {

	@Autowired
	private WateringService wateringService;

	@RequestMapping("/status")
	private Watering getStatusData(){
		return wateringService.getStatus();
	}
}
