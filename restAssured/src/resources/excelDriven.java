package resources;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import resources.*;
public class excelDriven {

	
	@Test
	public void addBook() throws IOException
	{
		dataDriven d=new dataDriven();
		ArrayList data=d.getData("RestAddPlace","RestAssured");
		
		
		
		HashMap<String, Object>  map = new HashMap<>();
		map.put("accuracy", data.get(1));
		map.put("name", data.get(2));
		map.put("phone_number", data.get(3));
		map.put("address", data.get(4));
		map.put("types", data.get(5));
		map.put("website", data.get(6));
		map.put("language", data.get(7));
		
		HashMap<String, Object>  map2 = new HashMap<>();
		map2.put("lat", data.get(8));
		map2.put("lng", data.get(9));
		
		map.put("location", map2);
		
		
		
	
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response res = given().log().all().queryParam("key", "qaclick123")
				         .body(map).when().post("maps/api/place/add/json")
				         .then().assertThat().statusCode(200).extract().response();
		
		
		String response = res.asString();
		System.out.println(response);
		
		
	

	}
	
	
}
