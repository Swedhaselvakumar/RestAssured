package restAssured;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
//		1. Print No of courses returned by API
//
//		2.Print Purchase Amount
//
//		3. Print Title of the first course
//
//		4. Print All course titles and their respective Prices
//
//		5. Print no of copies sold by RPA Course
//
//		6. Verify if Sum of all Course prices matches with Purchase Amount


		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		String title = js.getString("courses[0].title");
		System.out.println(title);
		
		for(int i=0;i<count;i++) {
			
			String courseTitle = js.getString("courses["+i+"].title");
			System.out.println("Corse title: " + courseTitle);
			
			String courseprice = js.getString("courses["+i+"].price");
			System.out.println("Corse price: " + courseprice);
			
		 
		}
		
		System.out.println("Print no of copies sold by RPA");
        for(int i=0;i<count;i++) {
        	
        	String courseTitles = js.getString("courses["+i+"].title");
			 if(courseTitles.equalsIgnoreCase("RPA")) {
				 int copies = js.getInt("courses["+i+"].copies");		 
				 System.out.println("copies for RPA Course: " + copies);
			     break;
			 }
        }
		
	}

}
