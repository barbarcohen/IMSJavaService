package com.honeywell.ims.api.web;

import java.util.Date;

/**
 * Created by h134602 on 9/12/2016.
 */
public class Watering {

	private Date nextWateringDate;

	private String status;

	public Date getNextWateringDate() {
		return nextWateringDate;
	}

	public void setNextWateringDate(final Date nextWateringDate) {
		this.nextWateringDate = nextWateringDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}
}
