package com.infogroup.api.records;

import com.infogroup.api.types.AddressParsedFields;
import com.infogroup.api.types.GeoPoint;

public class BasicRecord extends CoreRecord {
	// basic
	public String Address;
	public AddressParsedFields AddressParsed;
	public String City;
	public String FirstName;
	public String LastName;
	public String MiddleInitial;
	public String Phone;
	public String PostalCode;
	public String StateProvince;
	public GeoPoint Location;
	public String CompanyName;

}
