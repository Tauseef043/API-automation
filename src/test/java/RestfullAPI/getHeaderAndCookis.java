package RestfullAPI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getHeaderAndCookis {

	
	private RequestSpecification spec;

	@BeforeTest()
	public void setup()
	{
				 spec= new RequestSpecBuilder()
				.setBaseUri("https://restful-booker.herokuapp.com/")
				.build();
		
	}
	@Test(enabled=false)
	public void getHeaderAndCOokis()
	{
		Response response = RestAssured.given(spec).get("/booking");
		
		//Get list of headers
		Headers headers= response.getHeaders();
		System.out.println("Headers: "+headers);
		
		//get single header
		Header header1= headers.get("Server");
		System.out.println("server name: "+header1.getName()+ " value:  "+header1.getValue());
		
		//OR
		String header2= response.header("Server");
		System.out.println("server name string: "+header2);
		
		
		//Get cookis
		
		Cookies cookies=response.getDetailedCookies();
		System.out.println("all cookies: "+ cookies);
			
		
		
	}
	@Test(enabled=true)
	public void addHeaderAndCookies()
	{
		Cookie cookie1= new Cookie.Builder("test cookied add name", "value of cookie 123").build();
		Header header11=new Header("test header add name", "value of header 123");
		spec.header(header11);
		
		Response response = RestAssured.given(spec)
				.cookie("Test cookie name","test cookie Value")
				.header("Test header name","test header Value")
				.log().all()
				.get("/booking");
		
		
	
		
		//Get cookis
		
		Cookies cookies=response.getDetailedCookies();
		System.out.println("all cookies: "+ cookies);
		//Get list of headers
				Headers headers= response.getHeaders();
				System.out.println("Headers: "+headers);
		
	}
}
