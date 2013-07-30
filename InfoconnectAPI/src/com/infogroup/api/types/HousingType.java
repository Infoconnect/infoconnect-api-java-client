package com.infogroup.api.types;

public enum HousingType {
	MULTI_FAMILY("Multi-Family Dwelling"), NURSING_HOME("Nursing Home"), RETIREMENT_HOME("Retirement Home"), SINGLE_FAMILY("Single Family Dwelling"), TRAILER_COURT(
			"Trailer Court"), UNKNOWN("Unknown");

	private String val;

	private HousingType(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}
