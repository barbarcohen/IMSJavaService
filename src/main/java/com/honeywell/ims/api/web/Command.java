package com.honeywell.ims.api.web;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Command {

	private String name;

	private boolean isSuccessful;

	public static Command result(String name, boolean isSuccessful) {
		return new Command(name, isSuccessful);
	}

	public static Command fail() {
		return new Command(null, false);
	}

	public Command() {
	}

	public Command(final String name, final boolean isSuccessful) {
		this.name = name;
		this.isSuccessful = isSuccessful;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setIsSuccessful(final boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
}
