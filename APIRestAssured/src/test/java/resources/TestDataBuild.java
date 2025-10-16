package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad() {

	
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
	
	return p;
	
	}
}
