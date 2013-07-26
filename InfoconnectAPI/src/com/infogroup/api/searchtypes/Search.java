package com.infogroup.api.searchtypes;

import java.util.ArrayList;
import java.util.List;

import com.infogroup.api.types.GeoPoint;

public class Search {
	public static final int RESOURCE_TYPE_CORE = 0;
	public static final int RESOURCE_TYPE_BASIC = 1;
	public static final int RESOURCE_TYPE_ENHANCED = 2;
	public static final int RESOURCE_TYPE_COUNTS = 3;
	public static final int RESOURCE_TYPE_SEARCH_MAX = 4;

	private static final String RESULT_TYPE_CORE = "core";
	private static final String RESULT_TYPE_BASIC = "basic";
	private static final String RESULT_TYPE_ENHANCED = "enhanced";
	private static final String RESULT_TYPE_COUNTS = "counts";

	protected String resourceType = RESULT_TYPE_CORE;

	public int Limit = 10;
	protected List<String> fields;

	public class PolygonPoints {
		List<GeoPoint> Points;
	}

	protected List<PolygonPoints> PolygonList;

	public void clearPolygons() {
		PolygonList.clear();
		PolygonList = null;
	}

	public void addPolygon(List<GeoPoint> poly) throws Exception {
		if (null == PolygonList) {
			PolygonList = new ArrayList<PolygonPoints>();
		} else if (5 == PolygonList.size()) {
			throw new Exception("Maxiumum of 5 polygons are allowed for Search");
		}

		PolygonPoints pts = new PolygonPoints();
		pts.Points = poly;
		PolygonList.add(pts);

	}

	public void clearCustomFields() {
		fields.clear();
		fields = null;
	}

	public void addCustomFields(String field) {
		if (null == fields) {
			fields = new ArrayList<String>();
		}

		fields.add(field);
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String type) {
		resourceType = type;
	}

	public void setResourceType(int type) {
		switch (type) {
		case RESOURCE_TYPE_CORE:
			resourceType = RESULT_TYPE_CORE;
			break;
		case RESOURCE_TYPE_BASIC:
			resourceType = RESULT_TYPE_BASIC;
			break;
		case RESOURCE_TYPE_ENHANCED:
			resourceType = RESULT_TYPE_ENHANCED;
			break;
		case RESOURCE_TYPE_COUNTS:
			resourceType = RESULT_TYPE_COUNTS;
			break;
		}
	}
}
