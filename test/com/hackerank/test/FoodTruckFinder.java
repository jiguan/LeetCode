package com.hackerank.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FoodTruckFinder {
	private final static String RESOURCE_BASE_URL = "http://data.sfgov.org/resource/bbb8-hzi6.json";
	private final static int RECORD_LIMIT = 100;
	private final static int PAGE_SIZE = 10;
	private final static int PRINT_PAD = 30;
	private final static String APP_TOKEN = "KQkkEf3eJAgFnXIPB0gTPMZKO";
	private static QueryBuilder builder;
	private static ObjectMapper objectMapper = new ObjectMapper();

	private static QueryBuilder prepareQueryBuilder(String time, String day, int offset) {
		if (builder == null) {
			builder = new QueryBuilder().select("applicant", "location").orderBy("applicant", QueryBuilder.Order.ASC)
					.orderBy("location", QueryBuilder.Order.ASC).distinct(true).limit(RECORD_LIMIT);
		}

		// Find a food truck is open and not closed yet
		builder.where("start24", QueryBuilder.Operator.LESS_EQ, time).and("end24", QueryBuilder.Operator.LARGE, time)
				.and("dayorder", QueryBuilder.Operator.EQ, day);

		builder.offset(offset);
		return builder;
	}

	private static List<FoodTruck> requestFoodTrucksInfo(int requestTimes) throws IOException {
		// Calculate the offset based on current number of request
		int offset = requestTimes * RECORD_LIMIT;

		Calendar calendar = GregorianCalendar.getInstance();
		String currTime = String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d",calendar.get(Calendar.MINUTE));

		// Foodtruck server's day format: Sunday = 0, Saturday = 6
		String dayOfWeek = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
		prepareQueryBuilder(currTime, dayOfWeek, offset);

		String appToken = (APP_TOKEN == null) ? "" : "$$app_token=" + APP_TOKEN + "&";
		String url = RESOURCE_BASE_URL + "?" + appToken + builder.build();
		String jsonString = sendGetRequest(url);

		List<FoodTruck> res = objectMapper.readValue(jsonString,
				objectMapper.getTypeFactory().constructCollectionType(List.class, FoodTruck.class));
		return res;
	}

	private static String sendGetRequest(String address) throws IOException {
		StringBuilder result = new StringBuilder();
		URL url = new URL(address);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() != 200) {
			throw new IOException(
					String.format("The response returned with an invalid code: %d", conn.getResponseCode()));
		}
		try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		}
		return result.toString();
	}

	private static String prettyPrint(FoodTruck foodTruck) {
		String applicant = String.format("%1$-" + PRINT_PAD + "s", foodTruck.getApplicant());
		String location = String.format("%1$-" + PRINT_PAD + "s", foodTruck.getLocation());
		return String.format("%s | %s", applicant, location);
	}

	public static void main(String[] args) {
		// Record how many time we have sent the request
		int requestTimes = 0;
		Scanner scanner = new Scanner(System.in);
		try {
			List<FoodTruck> res = requestFoodTrucksInfo(requestTimes);
			if (!res.isEmpty()) {
				// There is food truck data, print the header column
				FoodTruck header = new FoodTruck();
				System.out.println(prettyPrint(header));

				while (!res.isEmpty()) {
					for (int i = 0; i < res.size(); ++i) {
						if ((i + 1) % PAGE_SIZE == 0) {
							System.out.println("Press any key to show the next page.");
							scanner.nextLine();
						}
						System.out.println(prettyPrint(res.get(i)));
					}
					res = requestFoodTrucksInfo(++requestTimes);
				}

				System.out.println("There is no more data.");
			} else {
				System.out.println("Currently no food truck is open.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}

// Model
@JsonIgnoreProperties(ignoreUnknown = true)
class FoodTruck {
	private String applicant = "NAME";
	private String location = "ADDRESS";

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return String.format("FoodTruck [applicant=%s, location=%s]", applicant, location);
	}
}

// Helper class
class QueryBuilder {
	enum Operator {
		EQ(" = "), LARGE(" > "), LARGE_EQ(" >= "), LESS(" < "), LESS_EQ(" <= "), NOT_EQ(" != ");

		private String value;

		public String toString() {
			return value;
		}

		Operator(String value) {
			this.value = value;
		}
	}

	enum Order {
		ASC(" ASC"), DESC(" DESC");

		private String value;

		public String toString() {
			return value;
		}

		Order(String value) {
			this.value = value;
		}
	}

	private String select;
	private String where;
	private String orderby;
	private String offset;
	private String limit;
	private String groupby;

	public QueryBuilder select(String... columns) {
		StringBuffer sb = new StringBuffer();
		for (String column : columns) {
			sb.append(column).append(", ");
		}
		sb.setLength(sb.length() - 2);
		this.select = "$select=" + sb.toString();
		return this;
	}

	public QueryBuilder where(String column, Operator opeartor, String value) {
		this.where = "$where=" + buildCondition(column, opeartor, value);
		return this;
	}

	public QueryBuilder and(String column, Operator opeartor, String value) {
		if (this.where == null) {
			throw new UnsupportedOperationException("This method must be used after calling where function.");
		}
		this.where += " AND " + buildCondition(column, opeartor, value);
		return this;
	}

	public QueryBuilder or(String column, Operator opeartor, String value) {
		if (this.where == null) {
			throw new UnsupportedOperationException("This method must be used after calling where function.");
		}
		this.where += " OR " + buildCondition(column, opeartor, value);
		return this;
	}

	public QueryBuilder limit(int limit) {
		this.limit = "$limit=" + limit;
		return this;
	}

	public QueryBuilder offset(int offset) {
		this.offset = "$offset=" + offset;
		return this;
	}

	public QueryBuilder orderBy(String column, Order order) {
		if (this.orderby == null) {
			this.orderby = "$order=" + column + order;
		} else {
			this.orderby += ", " + column + order;
		}
		return this;
	}

	// SoQL doesn't support distinct keyword, we have to use groupby to do it.
	// Note if there is a orderby, we need to contain the same columns
	public QueryBuilder distinct(boolean distinct) {
		if (distinct) {
			this.groupby = this.select.replace("select", "group");
		} else {
			this.groupby = null;
		}
		return this;
	}

	private String buildCondition(String column, Operator opeartor, String value) {
		return column + opeartor + "'" + value + "'";
	}

	public String build() {
		String str = this.toString();
		str = str.replaceAll("\\s+", "%20");
		str = str.replaceAll("'", "%27");
		return str;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.select);
		if (this.where != null) {
			sb.append("&").append(this.where);
		}
		if (this.orderby != null) {
			sb.append("&").append(this.orderby);
		}
		if (this.offset != null) {
			sb.append("&").append(this.offset);
		}
		if (this.limit != null) {
			sb.append("&").append(this.limit);
		}
		if (this.groupby != null) {
			sb.append("&").append(this.groupby);
		}
		return sb.toString();
	}
}
