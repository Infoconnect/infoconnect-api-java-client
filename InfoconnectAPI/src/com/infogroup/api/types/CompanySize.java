package com.infogroup.api.types;

public enum CompanySize {
	EMPLOYEES_1_4("1-4"), EMPLOYEES_5_9("5-9"), EMPLOYEES_10_19("10-19"), EMPLOYEES_20_49("20-49"), EMPLOYEES_50_99("50-99"), EMPLOYEES_100_249("100-249"), EMPLOYEES_250_499(
			"250-499"), EMPLOYEES_500_999("500-999"), EMPLOYEES_1000_4999("1000-4999"), EMPLOYEES_5000_9999("5000-9999"), EMPLOYEES_10000("10000");

	private String val;

	private CompanySize(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}