package com.infogroup.api.types;

public enum AgeRange {
	AGE_18_24("18-24"), AGE_25_29("25-29"), AGE_30_34("30-34"), AGE_35_39("35-39"), AGE_40_44("40-44"), AGE_45_49("45-49"), AGE_50_54("50-54"), AGE_55_59(
			"55-59"), AGE_60_64("60-64"), AGE_65_69("65-69"), AGE_70_74("70-74"), AGE_75("75");

	private String val;

	private AgeRange(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}
