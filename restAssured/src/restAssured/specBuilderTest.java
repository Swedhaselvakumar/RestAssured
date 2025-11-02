package restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;



public class specBuilderTest {

	public static void main(String[] args) {
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937)");
		p.setWebsite("https://rahulshettyacademy.com");
		
		List<String> list = new ArrayList<>();
		list.add("shoe park");
		list.add("shop");
		
		p.setTypes(list);
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		p.setLocation(loc);
		
		
		//----These 2 things we will use for common things..........like request and expected response code..eg
//		RequestSpecification req = new RequestSpecBuilder()
//		ResponseSpecification resspec = new ResponseSpecBuilder()
//				
				
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		
		
		RequestSpecification res = given().spec(req)
				         .body(p);
		
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		Response response = res.when().post("maps/api/place/add/json")
				         .then().spec(resspec).extract().response();
		
		
		String responseString = response.asString();
		System.out.println(responseString);
		
		
		
		
		
		

	}

}
