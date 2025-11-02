package resources;


//enum is collection of methods...

public enum APIResources {
	
	
	//These method names should match with the name in feature file
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json"),
    UpdatePlaceAPI("maps/api/place/update/json");
	
	private String resource;
	
	//calling constructor ...
	APIResources(String resource){
		this.resource = resource;
		
	}
	
	
	
	public String PlaceAPI_Resource(){
		return resource;
	}
	
	
	
	
}
