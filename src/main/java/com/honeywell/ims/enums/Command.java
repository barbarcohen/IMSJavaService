package com.honeywell.ims.enums;

/**
 * Created by h134602 on 9/13/2016.
 */
public enum Command {
	on("on"),
	off("off");

	private String value;

	private Command(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
