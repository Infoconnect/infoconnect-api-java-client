package com.infogroup.api.types;

public class GeoPoint {
	public GeoPoint(String lat, String lng) {
		this.Latitude = lat;
		this.Longitude = lng;
	}

	public String Latitude;
	public String Longitude;
}