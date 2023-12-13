package RahulShettyAPI;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Config;

public class ViewProduct extends Config {

	private RequestSpecification spec;
	private Response response;

	@BeforeTest
	public void initialize() throws IOException {

		Configurations();
		spec = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseURL")).build();
	}

	@Test
	public void View_Product_Test() {

		spec.pathParams("productId", prop.getProperty("productID"));

		spec.header("Authorization", prop.getProperty("token"));
		response = (Response) RestAssured.given(spec).get("/product/get-product-detail/{productId}");

//		response = (Response) RestAssured.given(spec).header("Authorization", prop.getProperty("atoken"))
//				.get("/product/get-product-detail/{productId}");

		System.out.println(response.body().asPrettyString());

		System.out.println("/product/get-product-detail/{productId} : " + response.statusCode());
//		
		Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");

	}

}
