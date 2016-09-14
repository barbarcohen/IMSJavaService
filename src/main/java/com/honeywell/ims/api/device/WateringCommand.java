package com.honeywell.ims.api.device;

/**
 * Created by h134602 on 9/13/2016.
 */
public class WateringCommand {

	private long duration;

	public static WateringCommand create(long duration) {
		return new WateringCommand(duration);
	}

	public WateringCommand() {
	}

	public WateringCommand(final long duration) {
		this.duration = duration;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(final long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "WateringCommand{" +
			"duration=" + duration +
			'}';
	}
}
