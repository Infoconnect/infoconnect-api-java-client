package com.infogroup.api.searchtypes;

import java.util.ArrayList;
import java.util.List;

import com.infogroup.api.types.AddressParsedFields;
import com.infogroup.api.types.CitiesByStateProvinceFields;
import com.infogroup.api.types.CountiesByStateProvinceFields;
import com.infogroup.api.types.Ethnicity;
import com.infogroup.api.types.Gender;
import com.infogroup.api.types.GeoPoint;
import com.infogroup.api.types.GeoPointRadius;
import com.infogroup.api.types.RadiusPostalCode;
import com.infogroup.api.types.RecordStatus;
import com.infogroup.api.types.ResourceType;

public class Search {

	public ResourceType resourceType;

	public AddressParsedFields AddressParsed;
	public String city;
	public String phone;
	public String Firstname;
	public GeoPoint location;
	public String Lastname;
	public Gender Gender;
	public Ethnicity Ethnicity;
	public String middleInitial;
	protected List<String> PostalCode;
	public String StateProvince;
	public String County;

	public int Limit = 10;
	public int Offset = 0;
	protected List<String> fields;

	public boolean NthRecord;
	public GeoPoint sortCenter;

	public class PolygonPoints {
		List<GeoPoint> Points;
	}

	protected List<PolygonPoints> PolygonList;

	protected List<GeoPointRadius> RadiusCenterPointList;
	protected List<RadiusPostalCode> RadiusPostalCodeList;

	protected ArrayList<String> notid = null;
	protected ArrayList<String> Id = null;

	protected ArrayList<CitiesByStateProvinceFields> CitiesByStateProvince;
	protected ArrayList<CountiesByStateProvinceFields> CountiesByStateProvince;

	protected ArrayList<String> Fields;

	public String Email;
	public RecordStatus RecordStatus;

	/*
	 * helper functions for searchable fields
	 */

	public void clearOutputFields() {
		Fields.clear();
		Fields = null;
	}

	public void addOutputField(String field) {
		if (null == Fields) {
			Fields = new ArrayList<String>();
		}

		Fields.add(field);
	}

	public void clearPostalCodes() {
		PostalCode.clear();
		PostalCode = null;
	}

	public void addPostalCode(String zip) {
		if (null == PostalCode) {
			PostalCode = new ArrayList<String>();
		}

		if (!PostalCode.contains(zip)) {
			PostalCode.add(zip);
		}
	}

	public void clearCountyByState() {
		CountiesByStateProvince.clear();
		CountiesByStateProvince = null; // setting to null ensures it won't show
										// up in the JSON
	}

	public void addCountyByState(String county, String state) throws Exception {
		if (null == CountiesByStateProvince) {
			CountiesByStateProvince = new ArrayList<CountiesByStateProvinceFields>();
		} else if (999 == CountiesByStateProvince.size()) {
			throw new Exception("Too many search counties. 1000 max.");
		}

		CountiesByStateProvince.add(new CountiesByStateProvinceFields(county, state));
	}

	public void clearCityByState() {
		CitiesByStateProvince.clear();
		CitiesByStateProvince = null; // setting to null ensures it won't show
										// up in the JSON
	}

	public void addCityByState(String city, String state) throws Exception {
		if (null == CitiesByStateProvince) {
			CitiesByStateProvince = new ArrayList<CitiesByStateProvinceFields>();
		} else if (999 == CitiesByStateProvince.size()) {
			throw new Exception("Too many search cities. 1000 max.");
		}

		CitiesByStateProvince.add(new CitiesByStateProvinceFields(city, state));
	}

	public void clearExcludes() {
		notid.clear();
		notid = null; // setting to null ensures it won't show up in the JSON
	}

	public void excludeRecord(String id) throws Exception {
		if (null == notid) {
			notid = new ArrayList<String>();
		} else if (999 == notid.size()) {
			throw new Exception("Too many exclusions. 1000 max.");
		}

		if (!notid.contains(id)) {
			notid.add(id);
		}
	}

	public void clearIds() {
		Id.clear();
		Id = null; // setting to null ensures it won't show up in the JSON
	}

	public void addId(String id) throws Exception {
		if (null == Id) {
			Id = new ArrayList<String>();
		} else if (999 == notid.size()) {
			throw new Exception("Too many Ids. 1000 max.");
		}

		if (!Id.contains(id)) {
			Id.add(id);
		}
	}

	public void clearSearchPostalRadius() {
		RadiusCenterPointList.clear();
		RadiusCenterPointList = null;
	}

	public void addSearchPostalRadius(RadiusPostalCode zip) throws Exception {
		if (null == RadiusPostalCodeList) {
			RadiusPostalCodeList = new ArrayList<RadiusPostalCode>();
		} else if (5 == RadiusPostalCodeList.size()) {
			throw new Exception("Maxiumum of 5 postal codes are allowed for Radius Search");
		}

		RadiusPostalCodeList.add(zip);
	}

	public void clearSearchRadius() {
		RadiusCenterPointList.clear();
		RadiusCenterPointList = null;
	}

	public void addSearchRadius(GeoPointRadius pt) throws Exception {
		if (null == RadiusCenterPointList) {
			RadiusCenterPointList = new ArrayList<GeoPointRadius>();
		} else if (5 == RadiusCenterPointList.size()) {
			throw new Exception("Maxiumum of 5 points are allowed for Radius Search");
		}

		RadiusCenterPointList.add(pt);
	}

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

}
