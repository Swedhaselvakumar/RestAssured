package restAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	
   @Test(dataProvider="BookData")
	public void addbook(String isbn, String aisle) {
	   
	   RestAssured.baseURI = "http://216.10.245.166";
	   String response = given().header("Content-Type","application/json")
	   .body(Payload.Addbook(isbn,aisle)).when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
	   
	   JsonPath js = new JsonPath(response);
	   String id = js.get("ID");
	   System.out.println(id);
	}
   
   @DataProvider(name = "BookData")
   public Object[][] getData(){
	return new Object[][] {{"swedha","1234"},{"kabish","6789"},{"pokzz","3489"}};	   
   }

}
