package com.infogroup.api.types;

public enum LocationUnique {
	SITE_UNIQUE("Site Unique"), SITE_UNIQUE_PROF("Site Unique Professional"), SITE_UNIQUE_NONPROF("Site Unique Nonprofessional"), UNIQUE("Unique");

	private String val;

	private LocationUnique(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}