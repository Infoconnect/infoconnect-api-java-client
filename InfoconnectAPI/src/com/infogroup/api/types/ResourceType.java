package com.infogroup.api.types;

public enum ResourceType {
	CORE("core"), BASIC("basic"), ENHANCED("enhanced"), COUNTS("counts"), LIFESTYLE("lifestyle"), TARGETREADY("targetready");

	private String val;

	private ResourceType(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}
}
