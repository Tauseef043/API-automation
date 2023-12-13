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

public class AddToCart extends Config {
	private RequestSpecification spec;
	private Response response;

	@BeforeTest
	public void initialize() throws IOException {

		Configurations();
		spec = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseURL")).build();
	}

	@Test(priority = 1)
	public void addToCart_Test() {
		spec.header("Authorization", prop.getProperty("atoken"));

		JSONObject body = new JSONObject();
		body.put("_id", prop.getProperty("product_id"));

		response = (Response) RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString())
				.post("/user/add-to-cart");

		System.out.println("API STATUS CODE: " + response.statusCode());
		System.out.println(response.getBody().asPrettyString());

		Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
		Assert.assertEquals(response.jsonPath().getString("message"), "Product Added To Cart",
				"Product is not added to cart");
	}

	@Test(priority = 2)
	public void getCartProductDetails()
	{
		
		spec.header("Authorization", prop.getProperty("atoken"));
		response = RestAssured.given(spec)
		.get("/product/get-product-detail");
		
		
		System.out.println("API STATUS CODE: " + response.statusCode());
		System.out.println(response.getBody().asPrettyString());

		Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");

	}
	
	@Test(enabled=false)
	public void get_Cart_Count() {

		spec.header("Authorization", prop.getProperty("atoken"));
		response = RestAssured.given(spec)
		.get("/user/get-cart-count/6545f82a7244490f95dee8cd");

		System.out.println("API STATUS CODE: " + response.statusCode());
		System.out.println(response.getBody().asPrettyString());

		Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");

		
	}

}
