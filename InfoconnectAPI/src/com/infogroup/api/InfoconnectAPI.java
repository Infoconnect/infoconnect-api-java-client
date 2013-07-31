package com.infogroup.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.infogroup.api.records.Company;
import com.infogroup.api.records.Person;
import com.infogroup.api.records.SIC;
import com.infogroup.api.records.match.MatchResult;
import com.infogroup.api.searchtypes.CompanySearch;
import com.infogroup.api.searchtypes.MatchSearch;
import com.infogroup.api.searchtypes.PeopleSearch;
import com.infogroup.api.searchtypes.Search;
import com.infogroup.api.types.ResourceType;

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

	private Gson gson;
	private int statusCode;
	private String lastAnswer;

	public InfoconnectAPI() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapterFactory(new CustomEnumTypeAdapterFactory());
		gson = builder.create();
	}

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
	private class SICMatches {
		List<SIC> Matches;
	}

	public List<SIC> sics(String keyword) {
		return sics(keyword, false);
	}

	public List<SIC> sics(String keyword, boolean preferred) {
		String output;
		try {
			output = doGet(ResourceType.NULL, apiSics, "preferred=" + preferred + "&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		SICMatches answer = gson.fromJson(output, SICMatches.class);
		return answer.Matches;
	}

	public SIC sic(int code) {
		String output = doGet(ResourceType.NULL, apiSics + "/" + code);
		return gson.fromJson(output, SIC.class);
	}

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
		ResourceType oldType = search.resourceType;
		search.resourceType = ResourceType.COUNTS;
		// convert the object to JSON
		String jsonSearch = gson.toJson(search);
		search.resourceType = oldType; // now put it back to what it was

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

	public Company company(String id) {
		return company(ResourceType.BASIC, id);
	}

	public Company company(ResourceType type, String id) {
		String output = doGet(type, apiCompanies + "/" + id);
		return gson.fromJson(output, Company.class);
	}

	public List<Company> companies(CompanySearch search) {
		String jsonSearch = gson.toJson(search);
		return companies(jsonSearch);
	}

	public List<Company> companies(String search) {
		String output = doPost(apiCompanies, search);
		Type type = new TypeToken<List<Company>>() {
		}.getType();
		return gson.fromJson(output, type);
	}

	/*
	 * People APIs
	 */

	public Person person(String id) {
		return person(ResourceType.BASIC, id);
	}

	public Person person(ResourceType type, String id) {
		String output = doGet(type, apiPeople + "/" + id);
		return gson.fromJson(output, Person.class);
	}

	public List<Person> people(PeopleSearch search) {
		String jsonSearch = gson.toJson(search);
		return people(jsonSearch);
	}

	public List<Person> people(String search) {
		String output = doPost(apiPeople, search);
		if (statusCode != 200) {
			return null;
		}
		Type type = new TypeToken<List<Person>>() {
		}.getType();
		return gson.fromJson(output, type);
	}

	/*
	 * Match APIs
	 */

	public MatchResult match(MatchSearch search) {
		String jsonSearch = gson.toJson(search);
		return match(jsonSearch);
	}

	public MatchResult match(String search) {
		String output = doPost(apiMatch, search);
		return gson.fromJson(output, MatchResult.class);
	}

	public List<MatchResult> match(List<MatchSearch> search) {
		String jsonSearch = gson.toJson(search);
		return multiMatch(jsonSearch);
	}

	public List<MatchResult> multiMatch(String search) {
		Type type = new TypeToken<List<MatchResult>>() {
		}.getType();

		String output = doPost(apiMultiMatch, search);
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
		URLConnection client = null;
		;
		statusCode = 0;
		lastAnswer = null;
		try {
			client = new URL(infoConnectAPIRoot + api + "?" + query).openConnection();
			System.out.println("Request: POST " + client.getURL());
			System.out.println("Body: " + body);
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
			System.out.println("Response code: " + statusCode);

			answer = IOUtils.toString(response);
			System.out.println("Response: " + answer);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			HttpURLConnection httpConnection = (HttpURLConnection) client;
			try {
				answer = IOUtils.toString(httpConnection.getErrorStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		lastAnswer = answer;

		return answer;
	}

	private String doGet(ResourceType type, String api) {
		return doGet(type, api, null);
	}

	private String doGet(ResourceType type, String api, String q) {
		String query = "apikey=" + apiKey;
		if (ResourceType.NULL != type) {
			query += "&resourceType=" + type;
		}
		if (null != q && !q.isEmpty()) {
			query += "&" + q;
		}
		String answer = null;
		URLConnection client;
		statusCode = 0;
		lastAnswer = null;
		try {
			client = new URL(infoConnectAPIRoot + api + "?" + query).openConnection();
			System.out.println("Request: GET " + client.getURL());
			client.setRequestProperty("Accept", "application/json");
			InputStream response = client.getInputStream();

			HttpURLConnection httpConnection = (HttpURLConnection) client;

			statusCode = httpConnection.getResponseCode();
			System.out.println("Response code: " + statusCode);
			answer = IOUtils.toString(response);
			System.out.println("Response: " + answer);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		lastAnswer = answer;
		return answer;
	}

	private class CustomEnumTypeAdapterFactory implements TypeAdapterFactory {
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			Class<T> rawType = (Class<T>) type.getRawType();
			if (!rawType.isEnum()) {
				return null;
			}

			final Map<String, T> stringToConstant = new HashMap<String, T>();
			for (T constant : rawType.getEnumConstants()) {
				stringToConstant.put(constant.toString(), constant);
			}

			return new TypeAdapter<T>() {
				public void write(JsonWriter out, T value) throws IOException {
					if (value == null) {
						out.nullValue();
					} else {
						out.value(value.toString());
					}
				}

				public T read(JsonReader reader) throws IOException {
					if (reader.peek() == JsonToken.NULL) {
						reader.nextNull();
						return null;
					} else {
						return stringToConstant.get(reader.nextString());
					}
				}
			};
		}

	}
}
