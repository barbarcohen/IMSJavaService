package com.honeywell.ims.api.web;

import java.util.Date;

import com.honeywell.ims.enums.WateringStatus;

/**
 * Created by h134602 on 9/12/2016.
 */
public class Watering {

	private Date nextWateringDate;

	private WateringStatus status;

	public Date getNextWateringDate() {
		return nextWateringDate;
	}

	public void setNextWateringDate(final Date nextWateringDate) {
		this.nextWateringDate = nextWateringDate;
	}

	public WateringStatus getStatus() {
		return status;
	}

	public void setStatus(final WateringStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Watering{" +
			"nextWateringDate=" + nextWateringDate +
			", status=" + status +
			'}';
	}
}
