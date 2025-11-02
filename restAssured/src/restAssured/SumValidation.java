package restAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	
	
	@Test
	public void sumOfCourse() {
		
		int sum = 0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		for(int i=0;i<count;i++) {
			int price = js.getInt("courses["+i+"].price");
		    int copies = js.getInt("courses["+i+"].copies");
		    int amount = price*copies;
		    System.out.println(amount);
		    sum = sum+amount;
		
		}
		
		System.out.println("The total sum of the amount: " + sum);
	    int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	
	}

}
