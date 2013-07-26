package com.infogroup.api.types;

public class RadiusPostalCode {
	public RadiusPostalCode(String zip, String radius) {
		this.PostalCode = zip;
		this.RadiusMiles = radius;
	}

	public String PostalCode;
	public String RadiusMiles;
}
