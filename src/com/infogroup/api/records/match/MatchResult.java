package com.infogroup.api.records.match;

import java.util.List;

public class MatchResult {
	public int MatchCount;
	public List<MatchScore> ScoreSummary;

	public class Matches {
		public List<MatchResultBasic> Common;
		public List<MatchResultCompany> Companies;
		public List<MatchResultPerson> People;
	}

	Matches Matches;
}
