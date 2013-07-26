package com.infogroup.api.types;


public class GeoPointRadius extends GeoPoint {
	public GeoPointRadius(String lat, String lng, int radius) {
		super(lat, lng);
		this.RadiusMiles = radius;
	}

	public int RadiusMiles;
}
