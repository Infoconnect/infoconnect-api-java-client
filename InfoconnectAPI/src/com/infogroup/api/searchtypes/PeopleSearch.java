package com.infogroup.api.searchtypes;

import com.infogroup.api.types.Lifestyle;
import com.infogroup.api.types.TargetReady;

public class PeopleSearch extends Search {
	// these are only support by People records
	public static final int RESOURCE_TYPE_LIFESTYLE = RESOURCE_TYPE_SEARCH_MAX + 1;
	public static final int RESOURCE_TYPE_TARGETREADY = RESOURCE_TYPE_SEARCH_MAX + 2;
	private static final String RESULT_TYPE_LIFESTYLE = "Lifestyle";
	private static final String RESULT_TYPE_TARGETREADY = "Targetready";

	@Override
	public void setResourceType(int type) {
		super.setResourceType(type);

		switch (type) {
		case RESOURCE_TYPE_LIFESTYLE:
			resourceType = RESULT_TYPE_LIFESTYLE;
			break;
		case RESOURCE_TYPE_TARGETREADY:
			resourceType = RESULT_TYPE_TARGETREADY;
			break;
		}
	}

	public Lifestyle Lifestyle;
	public TargetReady TargetReady;

}
