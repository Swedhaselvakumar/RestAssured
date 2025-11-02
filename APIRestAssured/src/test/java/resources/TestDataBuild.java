package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad(String name, String language, String address) {

	
	AddPlace p = new AddPlace();
	p.setAccuracy(50);
	p.setAddress(address);
	p.setLanguage(language);
	p.setName(name);
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
	
	return p;
	
	}
	
	
	public String deletePlacePayLoad(String PlaceId) {
				
		//we can use "escape json" online editor and change the json to string...
		//but here we are dynamically passing the placeid in runtime..so we r doing like this..
		
		return "{\r\n  \"place_id\" :  \""+PlaceId+"\"\r\n}";
	}
}
