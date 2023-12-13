package RestfullAPI;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PathParam {

	private RequestSpecification spec;
	@BeforeTest()
	public void setup()
	{
		 spec= new RequestSpecBuilder()
				.setBaseUri("https://restful-booker.herokuapp.com/")
				.build();
		
	}
	
	@Test(priority=1)
	public void getBookingIDWithoutFilter() {
		
		
//		RequestSpecification sepc;
		//now we are using request specification
		
	
	
		
		
//		String APIEndPoint = "/booking";
		Response response = RestAssured.given(spec).get("/booking");
		response.print();

		// verify response code

		Assert.assertEquals(response.statusCode(), 200, "Status Code should be 200");

		// verify atleast 1 booking id in response
		// our response is in json so we use JSON path to getvalue from response

		List<Integer> bookingIDS = response.jsonPath().getList("bookingid");

		Assert.assertFalse(bookingIDS.isEmpty(), "List of booking ids is empty");
	}
	
	
	
	
	@Test(priority=2)
	public void getBookingID() {
//		String URL = "https://restful-booker.herokuapp.com/booking/15";
		
		spec.pathParam("bookingId", 15);
		
		
//		Response response = RestAssured.given(spec).get("booking/15"); //or we can use method 2
		
		Response response = RestAssured.given(spec).get("/booking/{bookingId}");
		

		response.print();
		Assert.assertEquals(response.statusCode(), 200, "Status Code should be 200");

		//another method for assertion
		//verify response fields
		
		SoftAssert softAssert=new SoftAssert();
		
		String fName=response.jsonPath().getString("firstname");
		softAssert.assertEquals(fName, "Josh"," First name is not as exected");
		
		String lName=response.jsonPath().getString("lastname");
		softAssert.assertEquals(lName, "Allen"," Last name is not as exected");
		
		int tPrice=response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(tPrice, "111"," total price is not as exected");
		
		boolean depositPaid=response.jsonPath().getBoolean("depositpaid");
		softAssert.assertEquals(depositPaid, "true"," depositPaid is not as exected");
		
		String checkin=response.jsonPath().getString("checkin");
//		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
		
		String checkout=response.jsonPath().getString("checkout");
//		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
		
		softAssert.assertAll();
		
		
	}

}
