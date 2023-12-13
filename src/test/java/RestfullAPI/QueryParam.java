package RestfullAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class QueryParam {
	private RequestSpecification spec;

	@BeforeTest()
	public void setup()
	{
				 spec= new RequestSpecBuilder()
				.setBaseUri("https://restful-booker.herokuapp.com/")
				.build();
		
	}
	
	@Test(priority=1)
	public void QUeryParam() {
		

		//QUeryParam
	spec.queryParam("firstname", "John");
		
		
//		String APIEndPoint = "/booking";
//		Response response = RestAssured.given(spec).get("/booking?firstname=John"); //either we can use query param same is trah ya query param method se
		
	
	Response response = RestAssured.given(spec).get("/booking"); 
	
	response.print();

		
		// verify response code

		Assert.assertEquals(response.statusCode(), 200, "Status Code should be 200");

		// verify atleast 1 booking id in response
		// our response is in json so we use JSON path to getvalue from response

		List<Integer> bookingIDS = response.jsonPath().getList("bookingid");

		Assert.assertFalse(bookingIDS.isEmpty(), "List of booking ids is empty");
	}
	

}
