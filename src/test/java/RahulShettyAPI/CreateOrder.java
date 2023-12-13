package RahulShettyAPI;

import java.io.IOException;

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

public class CreateOrder extends Config{
	private RequestSpecification spec;
	private Response response;
	@BeforeTest
	public void initialize() throws IOException {

		Configurations();
		spec = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseURL")).build();
	}

	@Test(priority = 1)
	public void addToCart_Test() {
		
		spec.header("Authorization", prop.getProperty("token"));

		JSONObject Order = new JSONObject();
		JSONObject body = new JSONObject();
		Order.put("country", "Pakistan");
		Order.put("productOrderedId", "6262e95ae26b7e1a10e89bf0");
		Order.put("orders", body);
		
		response = (Response) RestAssured.given(spec).contentType(ContentType.JSON).body(Order.toString())
				.post("/order/create-order");

		System.out.println("API STATUS CODE: " + response.statusCode());
		System.out.println(response.getBody().asPrettyString());

		Assert.assertEquals(response.statusCode(), 201, "Status code should be 201");
		Assert.assertEquals(response.jsonPath().getString("message"), "Order Placed Successfully",
				"Product is not added to cart");

		
		
		
		
	}
	

}
