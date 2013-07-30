package com.infogroup.api.types;

public enum HomeValueRange {
	VALUE_1_24999("1-24999"), VALUE_25000_49999("25000-49999"), VALUE_50000_74999("50000-74999"), VALUE_75000_99999("75000-99999"), VALUE_100000_124999(
			"100000-124999"), VALUE_125000_149999("125000-149999"), VALUE_150000_174999("150000-174999"), VALUE_175000_199999("175000-199999"), VALUE_200000_249999(
			"200000-249999"), VALUE_250000_299999("250000-299999"), VALUE_300000_349999("300000-349999"), VALUE_350000_399999("350000-399999"), VALUE_400000_449999(
			"400000-449999"), VALUE_450000_499999("450000-499999"), VALUE_500000_599999("500000-599999"), VALUE_600000_699999("600000-699999"), VALUE_700000_799999(
			"700000-799999"), VALUE_800000_899999("800000-899999"), VALUE_900000_999999("900000-999999"), VALUE_1000000("1000000");

	private String val;

	private HomeValueRange(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}
