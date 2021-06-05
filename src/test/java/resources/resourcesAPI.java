package resources;
// enum is special class in java which will sore constant and methods
public enum resourcesAPI {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
     private String resource;
	resourcesAPI(String resource) {
		 this.resource=resource; //assign the resource to the local resource variable
	}
	public String getResourcesAPI()
	{
	  return resource;

	}

}
