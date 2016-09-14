package com.honeywell.ims.domain;

/**
 * Created by h134602 on 9/13/2016.
 */
public class RainProbability {

	private int rainProbability;
	private double downfall;

	public RainProbability(final int rainProbability, final double downfall) {
		this.rainProbability = rainProbability;
		this.downfall = downfall;
	}

	public int getRainProbability() {
		return rainProbability;
	}

	public double getDownfall() {
		return downfall;
	}

	@Override
	public String toString() {
		return "RainProbability{" +
			"rainProbability=" + rainProbability +
			", downfall=" + downfall +
			'}';
	}
}
