package com.honeywell.ims.api.weather;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Weather {

	private int id;
	private String description;
	private String main;
	private String icon;

	private double percentage;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getMain() {
		return main;
	}

	public void setMain(final String main) {
		this.main = main;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(final String icon) {
		this.icon = icon;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(final double percentage) {
		this.percentage = percentage;
	}
}
