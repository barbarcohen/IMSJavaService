package com.honeywell.ims.domain;

/**
 * Created by h134602 on 9/13/2016.
 */
public class RainProbability {

	private int rainProbability;
	private int downfall;

	public RainProbability(final int rainProbability, final int downfall) {
		this.rainProbability = rainProbability;
		this.downfall = downfall;
	}

	public int getRainProbability() {
		return rainProbability;
	}

	public int getDownfall() {
		return downfall;
	}
}
