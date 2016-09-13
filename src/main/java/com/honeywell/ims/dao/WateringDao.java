package com.honeywell.ims.dao;

import org.springframework.stereotype.Repository;

import com.honeywell.ims.enums.WateringStatus;

/**
 * Created by h134602 on 9/13/2016.
 */
@Repository
public class WateringDao {

	private WateringStatus wateringStatus = null;

	public void saveStatus(WateringStatus status) {
		this.wateringStatus = status;
	}

	public WateringStatus getStatus() {
		return this.wateringStatus;
	}
}
