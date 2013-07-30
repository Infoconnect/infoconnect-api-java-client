package com.infogroup.api.searchtypes;

import java.util.ArrayList;
import java.util.List;

import com.infogroup.api.types.CompanySales;
import com.infogroup.api.types.CompanySize;
import com.infogroup.api.types.CreditRatingScore;

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

	public String CompanyName;
	public Long CorporateEmployeesSizeActual = null;
	public CreditRatingScore CreditRatingScore;
	protected List<CompanySize> CorporateEmployeesSizeRange = null;
	public Long CorporateSalesVolumeActual = null;
	protected List<CompanySales> CorporateSalesVolumeRange = null;

	public Long LocationEmployeesSizeActual = null;
	protected List<CompanySize> LocationEmployeesSizeRange = null;
	public Long LocationSalesVolumeActual = null;
	protected List<CompanySales> LocationSalesVolumeRange = null;

	public boolean IsFranchise;
	public String EIN;

	public String PrimaryNaics;
	public String PrimarySic;
	public String TickerSymbol;

	protected List<String> Naics = null;
	protected List<String> Sic = null;
	protected List<String> StatesProvinces = null;

	/*
	 * helper functions for company specific search fields
	 */
	public void clearNaicsCodes() {
		Naics.clear();
		Naics = null;
	}

	public void addNaicsCode(String code) throws Exception {
		if (null == Naics) {
			Naics = new ArrayList<String>();
		} else if (1000 == Naics.size()) {
			throw new Exception("Maxiumum of 1000 NAICS codes are allowed for Search");
		}

		if (!Naics.contains(code)) {
			Naics.add(code);
		}
	}

	public void clearSICs() {
		Sic.clear();
		Sic = null;
	}

	public void addSICCode(String code) throws Exception {
		if (null == Sic) {
			Sic = new ArrayList<String>();
		} else if (1000 == Sic.size()) {
			throw new Exception("Maxiumum of 1000 SIC codes are allowed for Search");
		}

		if (!Sic.contains(code)) {
			Sic.add(code);
		}
	}

	public void clearStatesProvinces() {
		StatesProvinces.clear();
		StatesProvinces = null;
	}

	public void addStatesProvinces(String state) throws Exception {
		if (null == StatesProvinces) {
			StatesProvinces = new ArrayList<String>();
		} else if (1000 == StatesProvinces.size()) {
			throw new Exception("Maxiumum of 1000 States/Provinces are allowed for Search");
		}

		if (!StatesProvinces.contains(state)) {
			StatesProvinces.add(state);
		}
	}

	public void clearEmployeeSizeRange() {
		CorporateEmployeesSizeRange.clear();
		CorporateEmployeesSizeRange = null;
	}

	public void addCorpEmployeeSizeRange(CompanySize size) {
		if (null == CorporateEmployeesSizeRange) {
			CorporateEmployeesSizeRange = new ArrayList<CompanySize>();
		}

		if (!CorporateEmployeesSizeRange.contains(size)) {
			CorporateEmployeesSizeRange.add(size);
		}
	}

	public void clearLocationEmployeeSizeRange() {
		LocationEmployeesSizeRange.clear();
		LocationEmployeesSizeRange = null;
	}

	public void addLocationEmployeeSizeRange(CompanySize size) {
		if (null == LocationEmployeesSizeRange) {
			LocationEmployeesSizeRange = new ArrayList<CompanySize>();
		}

		if (!LocationEmployeesSizeRange.contains(size)) {
			LocationEmployeesSizeRange.add(size);
		}
	}

	public void clearCorpSalesVolumnRange() {
		CorporateSalesVolumeRange.clear();
		CorporateSalesVolumeRange = null;
	}

	public void clearLocationSalesVolumnRange() {
		CorporateSalesVolumeRange.clear();
		CorporateSalesVolumeRange = null;
	}

	public void addLocationSalesVolumnRange(CompanySales range) throws Exception {
		if (null == LocationSalesVolumeRange) {
			LocationSalesVolumeRange = new ArrayList<CompanySales>();
		}

		if (!LocationSalesVolumeRange.contains(range)) {
			LocationSalesVolumeRange.add(range);
		}
	}

	public void addCorpSalesVolumnRange(CompanySales range) throws Exception {
		if (null == CorporateSalesVolumeRange) {
			CorporateSalesVolumeRange = new ArrayList<CompanySales>();
		}

		if (!CorporateSalesVolumeRange.contains(range)) {
			CorporateSalesVolumeRange.add(range);
		}
	}
}
