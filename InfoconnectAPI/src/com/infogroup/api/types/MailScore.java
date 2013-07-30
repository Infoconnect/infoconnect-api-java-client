package com.infogroup.api.types;

public enum MailScore {
	ACCURATE("Accurate"), LIKELY("Likely"), QUESTIONABLE("Questionable"), UNLIKELY("Unlikely"), UNDELIVERABLE("Undeliverable");

	private String val;

	private MailScore(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}
