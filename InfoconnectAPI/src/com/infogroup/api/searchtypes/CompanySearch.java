package com.infogroup.api.searchtypes;

import java.util.ArrayList;
import java.util.List;

import com.infogroup.api.types.AddressParsedFields;
import com.infogroup.api.types.CitiesByStateProvinceFields;
import com.infogroup.api.types.GeoPoint;

/**
 * 
 * @author craigs
 * 
 *         This uses the Long,Integer, etc objects rather than the primitives so
 *         they can be set to null. With GSON null ensures the value isn't
 *         written out to the JSON version of the object which can mess things
 *         up for numeric values since primitives are initialized to zero which
 *         isn't really what you want when used in a search.
 */

public class CompanySearch extends Search {
	private static final int SIZE_RANGE_1_4 = 0;
	private static final int SIZE_RANGE_5_9 = 1;
	private static final int SIZE_RANGE_10_19 = 2;
	private static final int SIZE_RANGE_20_49 = 3;
	private static final int SIZE_RANGE_50_99 = 4;
	private static final int SIZE_RANGE_100_249 = 5;
	private static final int SIZE_RANGE_250_499 = 6;
	private static final int SIZE_RANGE_500_999 = 7;
	private static final int SIZE_RANGE_1000_4999 = 8;
	private static final int SIZE_RANGE_5000_9999 = 9;
	private static final int SIZE_RANGE_10000 = 10;

	private static final String SIZE_RANGE_1_4_VALUE = "1-4";
	private static final String SIZE_RANGE_5_9_VALUE = "5-9";
	private static final String SIZE_RANGE_10_19_VALUE = "10-19";
	private static final String SIZE_RANGE_20_49_VALUE = "20-49";
	private static final String SIZE_RANGE_50_99_VALUE = "50-99";
	private static final String SIZE_RANGE_100_249_VALUE = "100-249";
	private static final String SIZE_RANGE_250_499_VALUE = "250-499";
	private static final String SIZE_RANGE_500_999_VALUE = "500-999";
	private static final String SIZE_RANGE_1000_4999_VALUE = "1000-4999";
	private static final String SIZE_RANGE_5000_9999_VALUE = "5000-9999";
	private static final String SIZE_RANGE_10000_VALUE = "10000";
	private static final int SALES_VOLUMN_RANGE_1_500K = 0;

	public AddressParsedFields AddressParsed;
	public String city;
	protected ArrayList<CitiesByStateProvinceFields> CitiesByStateProvince;
	public String companyname;
	public Long CorporateEmployeesSizeActual = null;

	protected List<String> CorporateEmployeesSizeRange = null;
	public Long CorporateSalesVolumeActual = null;
	protected List<String> CorporateSalesVolumeRange = null;
	public String StateProvince;

	public boolean NthRecord;
	public GeoPoint sortCenter;
	public ArrayList<String> notid = null;
	public int Offset = 0;
	public String phone;

	/*
	 * helper functions
	 */

	public void clearExcludes() {
		notid.clear();
		notid = null; // setting to null ensures it won't show up in the JSON
	}

	public void excludeCompany(String id) throws Exception {
		if (null == notid) {
			notid = new ArrayList<String>();
		} else if (999 == notid.size()) {
			throw new Exception("Too many exclusions. 1000 max.");
		}

		if (!notid.contains(id)) {
			notid.add(id);
		}
	}

	public void addCityByState(String city, String state) throws Exception {
		if (null == CitiesByStateProvince) {
			CitiesByStateProvince = new ArrayList<CitiesByStateProvinceFields>();
		} else if (999 == CitiesByStateProvince.size()) {
			throw new Exception("Too many search cities. 1000 max.");
		}

		CitiesByStateProvince.add(new CitiesByStateProvinceFields(city, state));
	}

	public void clearEmployeeSizeRange() {
		CorporateEmployeesSizeRange.clear();
		CorporateEmployeesSizeRange = null;
	}

	public void addCorpEmployeeSizeRange(int range) throws Exception {
		if (null == CorporateEmployeesSizeRange) {
			CorporateEmployeesSizeRange = new ArrayList<String>();
		}

		switch (range) {
		case SIZE_RANGE_1_4:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_1_4_VALUE));
			break;
		case SIZE_RANGE_5_9:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_5_9_VALUE));
			break;
		case SIZE_RANGE_10_19:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_10_19_VALUE));
			break;
		case SIZE_RANGE_20_49:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_20_49_VALUE));
			break;
		case SIZE_RANGE_50_99:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_50_99_VALUE));
			break;
		case SIZE_RANGE_100_249:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_100_249_VALUE));
			break;
		case SIZE_RANGE_250_499:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_250_499_VALUE));
			break;
		case SIZE_RANGE_500_999:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_500_999_VALUE));
			break;
		case SIZE_RANGE_1000_4999:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_1000_4999_VALUE));
			break;
		case SIZE_RANGE_5000_9999:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_5000_9999_VALUE));
			break;
		case SIZE_RANGE_10000:
			CorporateEmployeesSizeRange.add(new String(SIZE_RANGE_10000_VALUE));
			break;
		}
	}

	public void clearCorpSalesVolumnRange() {
		CorporateSalesVolumeRange.clear();
		CorporateSalesVolumeRange = null;
	}

	public void addCorpSalesVolumnRange(int range) throws Exception {
		throw new Exception("Not implemented");
		/*
		 * if (null == CorporateSalesVolumeRange) { CorporateSalesVolumeRange =
		 * new ArrayList<String>(); }
		 */
		/*
		 * 
		 * 1-500000 500000-1000000 1000000-2500000 2500000-5000000
		 * 5000000-10000000 10000000-20000000 20000000-50000000
		 * 50000000-100000000 100000000-500000000 500000000-1000000000
		 * 1000000000
		 */
		/*
		 * switch (range) { case SALES_VOLUMN_RANGE_1_500K:
		 * CorporateSalesVolumeRange.add(new String("1-500000")); }
		 */
	}
}
