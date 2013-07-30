package com.infogroup.api.types;

public enum Gender {
	MALE("Male"), FEMALE("Female"), UNKNOWN("Unknown");

	private String val;

	private Gender(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}
