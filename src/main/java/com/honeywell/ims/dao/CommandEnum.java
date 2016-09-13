package com.honeywell.ims.dao;

/**
 * Created by h134602 on 9/13/2016.
 */
public enum CommandEnum {
	on("on"),
	off("off");

	private String value;

	private CommandEnum(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
