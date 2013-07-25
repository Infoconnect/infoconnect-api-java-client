package com.infogroup.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infogroup.api.records.Company;
import com.infogroup.api.records.Person;
import com.infogroup.api.records.match.MatchResult;
import com.infogroup.api.searchtypes.CompanySearch;
import com.infogroup.api.searchtypes.MatchSearch;
import com.infogroup.api.searchtypes.PeopleSearch;
import com.infogroup.api.searchtypes.Search;

public class InfoconnectAPI {

	private static final String infoConnectAPIRoot = "https://api.infoconnect.com";
	private static final String apiCompanies = "/v1/companies";
	private static final String apiPeople = "/v1/people";
	private static final String apiSics = "/v1/sics";
	private static final String apiMatch = "/v3/match";
	private static final String apiMultiMatch = "/v3/multiplematch";

	public static final int FORMAT_XML = 0;
	private static final String MIME_TYPE_XML = "application/xml";
	public static final int FORMAT_JSON = 1;
	private static final String MIME_TYPE_JSON = "application/json";

	private String apiKey = "";
	private String resultFormat = MIME_TYPE_JSON;
	private String postFormat = MIME_TYPE_JSON;

	private Gson gson = new Gson();
	private int statusCode;
	private String lastAnswer;

	public static class Counts {
		public int MatchCount;
	}

	public void setApiKey(String key) {
		apiKey = key;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setResultFormat(int type) {
		switch (type) {
		case FORMAT_XML:
			resultFormat = MIME_TYPE_XML;
			break;
		case FORMAT_JSON:
			resultFormat = MIME_TYPE_JSON;
			break;
		}
	}

	public void setPostDataFormat(int type) {
		switch (type) {
		case FORMAT_XML:
			postFormat = MIME_TYPE_XML;
			break;
		case FORMAT_JSON:
			postFormat = MIME_TYPE_JSON;
			break;
		}
	}

	/*
	 * SIC APIs
	 */
	// TODO: implement these

	/*
	 * Count APIs
	 */

	public int count(CompanySearch search) {
		return count(apiCompanies, search);
	}

	public int count(PeopleSearch search) {
		return count(apiPeople, search);
	}

	private int count(String api, Search search) {
		// need to override the resource type to be 'counts'
		String oldType = search.getResourceType();
		search.setResourceType(Search.RESOURCE_TYPE_COUNTS);
		// convert the object to JSON
		String jsonSearch = gson.toJson(search);
		search.setResourceType(oldType); // now put it back to what it was

		System.out.println(jsonSearch);

		String output = doPost(api + "/count", jsonSearch);
		Counts c = new Counts();
		if (null != output && !output.isEmpty()) {
			c = gson.fromJson(output, Counts.class);
		}
		return c.MatchCount;
	}

	/*
	 * Company APIs
	 */

	public Company company(int id) {
		String output = doGet(apiCompanies + "/" + id);
		return gson.fromJson(output, Company.class);
	}

	public List<Company> companies(CompanySearch search) {
		String jsonSearch = gson.toJson(search);
		System.out.println(jsonSearch);
		return companies(jsonSearch);
	}

	public List<Company> companies(String search) {
		String output = doPost(apiCompanies, search);
		Type type = new TypeToken<List<Company>>() {
		}.getType();
		System.out.println(output);
		return gson.fromJson(output, type);
	}

	/*
	 * People APIs
	 */

	public List<Person> people(PeopleSearch search) {
		String jsonSearch = gson.toJson(search);
		System.out.println(jsonSearch);
		return people(jsonSearch);
	}

	public List<Person> people(String search) {
		String output = doPost(apiPeople, search);
		Type type = new TypeToken<List<Person>>() {
		}.getType();
		System.out.println(output);
		return gson.fromJson(output, type);
	}

	/*
	 * Match APIs
	 */

	public MatchResult match(MatchSearch search) {
		String jsonSearch = gson.toJson(search);
		System.out.println(jsonSearch);
		return match(jsonSearch);
	}

	public MatchResult match(String search) {
		String output = doPost(apiMatch, search);
		System.out.println(output);
		return gson.fromJson(output, MatchResult.class);
	}

	public List<MatchResult> match(List<MatchSearch> search) {
		String jsonSearch = gson.toJson(search);
		System.out.println(jsonSearch);
		return multiMatch(jsonSearch);
	}

	public List<MatchResult> multiMatch(String search) {
		Type type = new TypeToken<List<MatchResult>>() {
		}.getType();

		String output = doPost(apiMultiMatch, search);
		System.out.println(output);
		return gson.fromJson(output, type);
	}

	// TODO - Temporary until proper error handling is in place
	public String getLastAnswer() {
		return lastAnswer;
	}

	public int getLastStatus() {
		return statusCode;
	}

	/*
	 * HTTP functions
	 */

	private String doPost(String api, String body) {
		String query = "apikey=" + apiKey;
		String answer = null;
		URLConnection client;
		statusCode = 0;
		lastAnswer = null;
		try {
			client = new URL(infoConnectAPIRoot + api + "?" + query).openConnection();
			client.setDoOutput(true);
			client.setRequestProperty("Content-Type", postFormat);
			client.setRequestProperty("Accept", resultFormat);
			OutputStream output = null;
			try {
				output = client.getOutputStream();
				output.write(body.getBytes("UTF-8"));
			} finally {
				if (output != null)
					try {
						output.close();
					} catch (IOException logOrIgnore) {
					}
			}
			InputStream response = client.getInputStream();
			HttpURLConnection httpConnection = (HttpURLConnection) client;

			statusCode = httpConnection.getResponseCode();
			answer = IOUtils.toString(response);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		lastAnswer = answer;

		return answer;
	}

	private String doGet(String api) {
		String query = "apikey=" + apiKey;
		String answer = null;
		URLConnection client;
		statusCode = 0;
		lastAnswer = null;
		try {
			client = new URL(infoConnectAPIRoot + api + "?" + query).openConnection();
			client.setRequestProperty("Accept", "application/json");
			InputStream response = client.getInputStream();

			HttpURLConnection httpConnection = (HttpURLConnection) client;

			statusCode = httpConnection.getResponseCode();

			answer = IOUtils.toString(response);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		lastAnswer = answer;
		return answer;
	}
}
