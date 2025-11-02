package restAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class Demo {

	public static void main(String[] args) {
		
		//POST API
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.postapi()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();

		//System.out.println(response);
		
		
		JsonPath js = new JsonPath(response); //json parsing
		String placeId = js.getString("place_id");
		
		System.out.println("placeId printing : " + placeId);
		
		//PUT API
		String updatedresponse = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.putapi(placeId)).when().put("maps/api/place/update/json")
		.then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
		
		//System.out.println(updatedresponse);
		
		
		//GET API
		String getresponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(getresponse);
		String addressresponse = js1.getString("address");
		
		System.out.println("Address GET: " + addressresponse);
		Assert.assertEquals(addressresponse, "70 winter walk, USA");
				
		
	}

}
