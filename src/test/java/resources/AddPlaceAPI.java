package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class AddPlaceAPI {
	
	public AddPlace AddPlacePOG(String name, String address, String language)
	{
		AddPlace ga=new AddPlace(); // create object for the parent class
		Location l=new Location();  // set a new object for the class  Location and access it here
		// first set the Lang and Lng variables
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ga.setLocation(l); // send the object l to setter method
		ga.setAccuracy(50);
		ga.setAddress(address);
		ga.setName(name);
		ga.setPhone_number("(+91) 983 893 3937");
		// create a list of string array
		List<String> tp=new ArrayList<String>(); 
		//  add the strings into the  Array list
		tp.add("shoe park");
		tp.add("shop");
		ga.setTypes( tp); // use the setter to set the array list
		ga.setWebsite("http://google.com");
		ga.setLanguage(language);
		return ga;
	}
	
	public String DeletePlaceIdPayload(String place_id)
	{
		
		return"{\r\n  \"place_id\":\""+place_id+"\" \r\n}\r\n";
			

	}

}
