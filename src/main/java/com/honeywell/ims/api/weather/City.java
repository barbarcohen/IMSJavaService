package com.honeywell.ims.api.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/12/2016.
 */
public class City {

	private int id;

	private String name;

	@JsonProperty(value = "coord")
	private Coordinates coordinates;

	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(final Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public class Coordinates {

		@JsonProperty(value = "lat")
		private double lattitude;

		@JsonProperty(value = "lon")
		private double longtitude;

		public double getLattitude() {
			return lattitude;
		}

		public void setLattitude(final double lattitude) {
			this.lattitude = lattitude;
		}

		public double getLongtitude() {
			return longtitude;
		}

		public void setLongtitude(final double longtitude) {
			this.longtitude = longtitude;
		}
	}
}
