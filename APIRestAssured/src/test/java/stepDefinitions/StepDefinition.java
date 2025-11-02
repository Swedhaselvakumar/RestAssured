package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	
	TestDataBuild data = new TestDataBuild();

	

	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	JsonPath js;
	static String place_id;
	
	@Given("Add Place PayLoad with {string} {string} {string}")
	public void add_place_pay_load_with(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification())
				 .body(data.addPlacePayLoad(name, language, address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		//constructor will be called ...(valueOf)
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.PlaceAPI_Resource());
		
		 resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		 
		 if(method.equalsIgnoreCase("POST")) {
		 response = res.when().post(resourceAPI.PlaceAPI_Resource());
		 }
		 else if(method.equalsIgnoreCase("GET")) {
			 response = res.when().get(resourceAPI.PlaceAPI_Resource());
		 }
		         
		
	}
		
	
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String ExpectedValue) {
		
		assertEquals(getJsonPath(response,keyvalue),ExpectedValue);		
	  
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		
		 place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		
		
	    user_calls_with_http_request(resource,"GET");
	    
	    String actualname = getJsonPath(response,"name");
	    assertEquals(actualname,expectedName);
	}
	
	@Given("DeletePlacePayload")
	public void delete_place_payload() throws IOException {
		
		//here we are not creating pojo class bc only one keyvalue is availabe, so directly converted it 
	//into string and we are passing it here for payload
		
	    res = given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
	}





}
