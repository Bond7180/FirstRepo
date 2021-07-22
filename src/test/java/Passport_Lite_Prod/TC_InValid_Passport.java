package Passport_Lite_Prod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import kong.unirest.Unirest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.JSONString;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.Request;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import files.ReUsableMethods;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

//import org.json.Assertions;

public class TC_InValid_Passport extends BaseClass {


	String p = LoadProperties().getProperty("baseUri_prod");

	String p1 = LoadProperties().getProperty("path_passport_lite");

	String ContentType1 = LoadProperties().getProperty("ContentType");
	String UserAgent1 = LoadProperties().getProperty("User-Agent");

	String Accept1 = LoadProperties().getProperty("Accept");
	String AcceptEncoding1 = LoadProperties().getProperty("Accept-Encoding");
	String Connection1 = LoadProperties().getProperty("Connection");
	String auth1 = LoadProperties().getProperty("auth");

	String K = LoadProperties().getProperty("api-key");
	String A = LoadProperties().getProperty("app-id");
	//String A = LoadProperties().getProperty("app-test-id_BLNK");


	String mode = LoadProperties().getProperty("mode");
	String customer_passport_number = LoadProperties().getProperty("customer_passport_number_Invalid");
	String passport_type = LoadProperties().getProperty("passport_type");
	String passport_expiry_date = LoadProperties().getProperty("passport_expiry_date");
	String customer_first_name = LoadProperties().getProperty("customer_first_name");
	String customer_last_name = LoadProperties().getProperty("customer_last_name");
	String customer_dob = LoadProperties().getProperty("customer_dob");
	String customer_gender = LoadProperties().getProperty("customer_gender");
	String customer_country = LoadProperties().getProperty("customer_country");
	
	String consent_Y = LoadProperties().getProperty("consent_Y");
	String consent_text_Passport = LoadProperties().getProperty("consent_text_Passport");
	

	@Test

	public void APIResponse() throws IOException {

		System.out.println("mode is:" + mode);
		System.out.println("customer_passport_number is:" + customer_passport_number);
		System.out.println("passport_type is:" + passport_type);
		System.out.println("passport_expiry_date is:" + passport_expiry_date);
		System.out.println("customer_first_name is:" + customer_first_name);
		System.out.println("customer_last_name is:" + customer_last_name);
		System.out.println("customer_dob is:" + customer_dob);
		System.out.println("customer_gender is:" + customer_gender);
		System.out.println("customer_country is:" + customer_country);
		
		
		System.out.println("consent_text_Passport is:" + consent_text_Passport);
		System.out.println("consent_Y is:" + consent_Y);

		// List<Map<String,Object>> requestList = new ArrayList<>();

		Map<String, Object> dt = new HashMap<>();
		// dt.put("mode", "sync");
		dt.put("customer_passport_number", customer_passport_number);
		dt.put("passport_type", passport_type);
		dt.put("passport_expiry_date", passport_expiry_date);
		dt.put("customer_first_name", customer_first_name);
		dt.put("customer_last_name", customer_last_name);
		dt.put("customer_dob", customer_dob);
		dt.put("customer_gender", customer_gender);
		dt.put("customer_country", customer_country);
		
		dt.put("consent", consent_Y);
		dt.put("consent_text", consent_text_Passport);
		System.out.println("Map is:" + dt);

		// requestList.add(dt);

		Map<String, Object> m1 = new HashMap<>();
		m1.put("mode", mode);
		m1.put("data", dt);

		String response = RestAssured.given()

				.header("Content-Type", ContentType1)

				// request.header("Content-Length","<calculated when request is sent>");
				// request.header("Host","<calculated when request is sent>");
				.header("User-Agent", UserAgent1).header("Accept", Accept1).header("Accept-Encoding", AcceptEncoding1)
				.header("Connection", Connection1).header("auth", auth1)

				.header("app-id", A).header("api-key", K)

				.baseUri(p).body(m1).when()

				.post(p1).then().assertThat().log().all().extract().response().asString();

		System.out.println("MAP :" + dt);

		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("response_code");

		String name = js.get("result.mrz_line_1");
		String msg = js.get("response_message");
		String bill = js.get("metadata.billable");

		// int id = js.get("response_code");

		System.out.println("mrz_line_1 is :" + name);
		System.out.println("Response Code is :" + id);
		System.out.println("Response Message is :" + msg);
		System.out.println("Billable is :" + bill);

		assertEquals(id,"106");
	}

}

