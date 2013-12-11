package com.infogroup.api.types;

public class GeoPoint {
	public GeoPoint() {
		this.Latitude = "";
		this.Longitude = "";
	}

	public GeoPoint(String lat, String lng) {
		this.Latitude = lat;
		this.Longitude = lng;
	}

	public String Latitude;
	public String Longitude;
}