package com.infogroup.api.types;

public enum RecordStatus {
	EXISTING("Existing"), NEW("New"), ALL("All");

	private String val;

	private RecordStatus(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}
