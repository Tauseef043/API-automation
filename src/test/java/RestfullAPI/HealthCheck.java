package RestfullAPI;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class HealthCheck {

	@Test(enabled = false)
	public void healthCheckTest() {
		given().when().get("https://restful-booker.herokuapp.com/ping").then().assertThat().statusCode(201);

	}

	@Test(enabled = false)
	public void getBookingIDWithoutFilter() {
		String URL = "https://restful-booker.herokuapp.com/booking";
		Response response = RestAssured.get(URL);
		response.print();

		// verify response code

		Assert.assertEquals(response.statusCode(), 200, "Status Code should be 200");

		// verify atleast 1 booking id in response
		// our response is in json so we use JSON path to getvalue from response

		List<Integer> bookingIDS = response.jsonPath().getList("bookingid");

		Assert.assertFalse(bookingIDS.isEmpty(), "List of booking ids is empty");
	}

	@Test(enabled = true)
	public void getBookingID() {
		String URL = "https://restful-booker.herokuapp.com/booking/15";
		Response response = RestAssured.get(URL);
		response.print();
		Assert.assertEquals(response.statusCode(), 200, "Status Code should be 200");

		//another method for assertion
		//verify response fields
		
		SoftAssert softAssert=new SoftAssert();
		
		String fName=response.jsonPath().getString("firstname");
		softAssert.assertEquals(fName, "ALI"," First name is not as exected");
		
		String lName=response.jsonPath().getString("lastname");
		softAssert.assertEquals(lName, "Haider"," Last name is not as exected");
		
		int tPrice=response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(tPrice, "Haider"," total price is not as exected");
		
		boolean depositPaid=response.jsonPath().getBoolean("depositpaid");
		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
		
		String checkin=response.jsonPath().getString("checkin");
//		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
		
		String checkout=response.jsonPath().getString("checkout");
//		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
		
		softAssert.assertAll();
		
		 
		
	}

	@Test(enabled = true)
	public void CreateBooking() {
		String URL = "https://restful-booker.herokuapp.com/booking";
		// APIdoc URL
		// https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBooking

		// for this we have added json in java maven dependenc
		// https://mvnrepository.com/artifact/org.json/json/20231013
		// create Json Body
		// get response
		// verification
//		/*******************************************

		// Create Json Object

		JSONObject body = new JSONObject();
		body.put("firstname", "ALI");
		body.put("lastname", "Haider");
		body.put("totalprice", "100");
		body.put("depositpaid", false);

		// bookingdates is another json inside the json so we created antoher object

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2013-02-23");
		bookingdates.put("checkout", "2014-02-23");
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Lunch");

		// we have created json body now we need to send in post request

		Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post(URL);
		response.print();
		
		
		
		SoftAssert softAssert=new SoftAssert();
		

		String fName=response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(fName, "ALI"," First name is not as exected");
		
		String lName=response.jsonPath().getString(".booking.lastname");
		softAssert.assertEquals(lName, "Haider"," Last name is not as exected");
		
		int tPrice=response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(tPrice, "Haider"," total price is not as exected");
		
		boolean depositPaid=response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
		
		String checkin=response.jsonPath().getString("booking.checkin");
//		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
		
		String checkout=response.jsonPath().getString("booking.checkout");
//		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
		
		softAssert.assertAll();
		
		
		

		/*
		 * 
		 * 
		 * HTTP/1.1 200 OK
		 * 
		 * { "firstname": "Sally", "lastname": "Brown", "totalprice": 111,
		 * "depositpaid": true, "bookingdates": { "checkin": "2013-02-23", "checkout":
		 * "2014-10-23" }, "additionalneeds": "Breakfast" }
		 * 
		 **/

	}
}
