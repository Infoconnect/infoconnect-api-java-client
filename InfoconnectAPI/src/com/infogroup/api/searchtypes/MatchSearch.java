package com.infogroup.api.searchtypes;

public class MatchSearch extends Search {
	public static final int REQUEST_TYPE_ANY = 0;
	public static final int REQUEST_TYPE_COMPANY = 1;
	public static final int REQUEST_TYPE_PERSON = 2;

	public String Confidence;
	protected String RequestType;
	protected String ResourceType;
	public String StreetAddress;
	public String StreetAddress2;

	public void setRequestType(int type) {
		switch (type) {
		case REQUEST_TYPE_ANY:
			RequestType = "Any";
			break;
		case REQUEST_TYPE_COMPANY:
			RequestType = "Company";
			break;
		case REQUEST_TYPE_PERSON:
			RequestType = "Person";
			break;
		}
	}
}
