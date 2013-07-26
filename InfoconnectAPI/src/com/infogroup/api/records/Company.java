package com.infogroup.api.records;

import java.util.List;

import com.infogroup.api.types.Contact;
import com.infogroup.api.types.Naics;
import com.infogroup.api.types.Sic;
import com.infogroup.api.types.Uri;

public class Company extends BasicRecord {

	// enhanced
	public String BusinessType;
	public List<Contact> Contacts;
	public long CorporateEmployeesSizeActual;
	public String CorporateEmployeesSizeRange;
	public long CorporateSalesVolumeActual;
	public String CorporateSalesVolumeRange;
	public String County;
	public String CreditRatingScore;
	public String EIN1;
	public String EIN2;
	public String EIN3;
	public String Fax;
	public String Franchise;
	public String Gender;
	public long LocationEmployeesSizeActual;
	public String LocationEmployeesSizeRange;
	public long LocationSalesVolumeActual;
	public String LocationSalesVolumeRange;
	public List<Naics> NaicsList;
	public String PrimaryNaics;
	public String PrimarySic;
	public List<Sic> SicList;
	public String TickerSymbol;
	public String Title;
	public String TollFree;
	public List<Uri> UriList;
	public String ZipPlus4;
}
