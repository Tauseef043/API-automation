package RestfullAPI;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutPatchCTest {

	@Test(enabled = false)
	public void putMethodReq() {

		String URL = "https://restful-booker.herokuapp.com/booking";
		// APIdoc URL
		// https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBooking

		// pehly create kia uski id get kr k usy update kr rahy
//		/*******************************************

		// Create Json Object

		JSONObject body = new JSONObject();
		body.put("firstname", "Hamza");
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

		int bookingid = response.jsonPath().getInt("bookingid");

		// now update kr rahy

		// Create Json Object

		JSONObject bodyUpdate = new JSONObject();
		bodyUpdate.put("firstname", "HMamza");
		bodyUpdate.put("lastname", "Tauseef");
		bodyUpdate.put("totalprice", "1200");
		bodyUpdate.put("depositpaid", true);

		// bookingdates is another json inside the json so we created antoher object

		JSONObject BookingDates = new JSONObject();
		BookingDates.put("checkin", "2023-12-23");
		BookingDates.put("checkout", "2019-12-23");
		bodyUpdate.put("bookingdates", BookingDates);
		bodyUpdate.put("additionalneeds", "dinner");

		// we have created json body now we need to send in post request

		Response responseUpdate = RestAssured.given().auth().basic("admin", "password123").contentType(ContentType.JSON).body(bodyUpdate.toString())
				.put("https://restful-booker.herokuapp.com/booking/" + bookingid);
		
		
		
		//assertion
//		assertionFOrUpdate(responseUpdate);
		
		Assert.assertEquals(responseUpdate.getStatusCode(), 200,"Reposne code should be 200");
		
		SoftAssert softAssert = new SoftAssert();

		String fName = responseUpdate.jsonPath().getString("firstname");
		softAssert.assertEquals(fName, "ALI", " First name is not as exected");

		String lName = responseUpdate.jsonPath().getString("lastname");
		softAssert.assertEquals(lName, "Haider", " Last name is not as exected");

		int tPrice = responseUpdate.jsonPath().getInt("totalprice");
		softAssert.assertEquals(tPrice, "Haider", " total price is not as exected");

		boolean depositPaid = responseUpdate.jsonPath().getBoolean("depositpaid");
		softAssert.assertEquals(depositPaid, "Haider", " depositPaid is not as exected");

		String checkin = responseUpdate.jsonPath().getString("checkin");
//		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");

		String checkout = responseUpdate.jsonPath().getString("checkout");
//		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");

		softAssert.assertAll();

	}

//	public void assertionFOrUpdate(Response response) {
//		SoftAssert softAssert = new SoftAssert();
//
//		String fName = response.jsonPath().getString("firstname");
//		softAssert.assertEquals(fName, "ALI", " First name is not as exected");
//
//		String lName = response.jsonPath().getString("lastname");
//		softAssert.assertEquals(lName, "Haider", " Last name is not as exected");
//
//		int tPrice = response.jsonPath().getInt("totalprice");
//		softAssert.assertEquals(tPrice, "Haider", " total price is not as exected");
//
//		boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
//		softAssert.assertEquals(depositPaid, "Haider", " depositPaid is not as exected");
//
//		String checkin = response.jsonPath().getString("checkin");
////		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
//
//		String checkout = response.jsonPath().getString("checkout");
////		softAssert.assertEquals(depositPaid, "Haider"," depositPaid is not as exected");
//
//		softAssert.assertAll();
//	}
	
	
	
	@Test(enabled = true)
	public void deleteReq()
	{
		String URL = "https://restful-booker.herokuapp.com/booking";
		// APIdoc URL
		// https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBooking

		// pehly create kia uski id get kr k usy update kr rahy
//		/*******************************************
//creating resource
		// Create Json Object

		JSONObject body = new JSONObject();
		body.put("firstname", "Hamza");
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

		int bookingid = response.jsonPath().getInt("bookingid");
		
		//now delete
		
		
		Response responseDel = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.delete("https://restful-booker.herokuapp.com/booking/" + bookingid);
	

		Assert.assertEquals(responseDel.statusCode(), 201,"reposne code should 201");
		
		Response responseGet=RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingid);
		responseGet.print();
		
		Assert.assertEquals(responseGet.body().asString().toLowerCase(), "not found","Response should not found");
	}
}
