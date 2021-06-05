package StepDefinition;
import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before
	public void before_Scenario() throws IOException
	{
		stepDefinition sd=new stepDefinition(); // create class in order to access its method and variables
		if (stepDefinition.place_id==null)  // if declared static variable can be accessed through class
			                                // otherwise can be accessed  through object of class
	 	  {
		  // Make sure this runs only if place_id not created by their test cases(ie. place_id is null)
		  // Create a place_id from by calling other functions(when is needed by test)
		 
		  sd.add_place_was_embeded_in_the_body_as_payload_with("Ramin", "1150 Klondike Rd", "Persian");
	  	  sd.request_was_sent_as_http_prorocal("AddPlaceAPI", "POST");
		  sd.verify_place_id_maps_to_using("Ramin", "GetPlaceAPI");
	 	  }
	}

}
