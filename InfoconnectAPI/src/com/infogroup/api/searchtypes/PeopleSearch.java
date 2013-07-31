package com.infogroup.api.searchtypes;

import java.util.ArrayList;
import java.util.List;

import com.infogroup.api.types.AgeRange;
import com.infogroup.api.types.HomeValueRange;
import com.infogroup.api.types.HousingType;
import com.infogroup.api.types.IncomeRange;
import com.infogroup.api.types.Lifestyle;
import com.infogroup.api.types.TargetReady;

public class PeopleSearch extends Search {

	protected List<String> ActualIncome = null;
	protected List<String> Age;
	protected List<AgeRange> AgeRange;
	public Boolean AreChildrenPresent;
	public String BirthMonth;
	public String EmailSha1Upper;

	protected List<HomeValueRange> HomeValueRange;
	public HousingType HousingType;

	public Boolean IncludeAllResidents;
	/*
	 * NOTE: IncludeHistoricalEmail is used to retrieve historical records. If
	 * set to true, only Basic ResourceType is valid. If used, must be true or
	 * false (default is false). This field can only be used in combination with
	 * Email or EmailSha1Upper.
	 */
	public Boolean IncludeHistoricalEmail;

	protected List<IncomeRange> IncomeRange;

	public Boolean IsHomeowner;

	public String MaritalStatus;
	public List<String> YearsAtResidence;
	public Long YearsInHome;

	public Lifestyle Lifestyle;
	public TargetReady TargetReady;

	/*
	 * Helper functions
	 */
	public void clearActualIncome() {
		ActualIncome.clear();
		ActualIncome = null;
	}

	public void addActualIncome(String val) throws Exception {
		if (null == ActualIncome) {
			ActualIncome = new ArrayList<String>();
		}

		if (!ActualIncome.contains(val)) {
			ActualIncome.add(val);
		}
	}

	public void clearIncomeRange() {
		IncomeRange.clear();
		IncomeRange = null;
	}

	public void addIncomeRange(IncomeRange val) {
		if (null == IncomeRange) {
			IncomeRange = new ArrayList<IncomeRange>();
		}

		if (!IncomeRange.contains(val)) {
			IncomeRange.add(val);
		}
	}

	public void clearHomeValueRange() {
		HomeValueRange.clear();
		HomeValueRange = null;
	}

	public void addHomeValueRange(HomeValueRange val) {
		if (null == HomeValueRange) {
			HomeValueRange = new ArrayList<HomeValueRange>();
		}

		if (!HomeValueRange.contains(val)) {
			HomeValueRange.add(val);
		}
	}

	/*
	 * NOTE: Age must be submitted as an array of values of values. Up to 1000
	 * values can be sent in one request. Valid values for Age are from 1
	 * through 65. Values under 18 will return no results.
	 */
	public void clearAge() {
		Age.clear();
		Age = null;
	}

	public void addAge(String val) {
		if (null == Age) {
			Age = new ArrayList<String>();
		}

		if (!Age.contains(val)) {
			Age.add(val);
		}
	}

	public void clearAgeRange() {
		AgeRange.clear();
		AgeRange = null;
	}

	public void addAgeRange(AgeRange val) {
		if (null == AgeRange) {
			AgeRange = new ArrayList<AgeRange>();
		}

		if (!AgeRange.contains(val)) {
			AgeRange.add(val);
		}
	}
}
