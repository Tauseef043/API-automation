package RahulShettyAPI;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.client.methods.RequestBuilder;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Config;

public class LoginUser extends Config {

	String user = "Test0011@yopmail.com";
	String Pass = "123456Aa@";
	String authToken = "";
	private FileOutputStream fileOut;
	private RequestSpecification spec;

	@BeforeTest
	public void initialize() throws IOException {

		Configurations();
		spec = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseURL")).build();
	}

	@Test(priority=1)
	public void LoginUserr() throws IOException {
//		Response responseDel = RestAssured.given().auth().preemptive().basic("admin", "password123")
//				.delete("https://restful-booker.herokuapp.com/booking/" + bookingid);

//		String url = "https://rahulshettyacademy.com/api/ecom/auth/login";

		JSONObject body = new JSONObject();
		body.put("userEmail", prop.getProperty("userEmail"));
		body.put("userPassword", prop.getProperty("userPassword"));

//		Response loginResponse = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post(url);

		Response loginResponse = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString())
				.post("/auth/login");

		authToken = loginResponse.jsonPath().getString("token");
//
		prop.setProperty("token", authToken);

		
		FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\inputData.properties");
		 prop.store(fileOut, "Application Properties");

//		System.out.println("orignal token is : " + authToken);
		System.out.println("file token is : " + prop.getProperty("token"));
		System.out.println("Login API Status code: " + loginResponse.statusCode());
//		System.out.println("response of login : "+loginResponse.getBody().asString());
		Assert.assertEquals(loginResponse.statusCode(), 200, "Response code should be 200 to login to user");

	}
}
