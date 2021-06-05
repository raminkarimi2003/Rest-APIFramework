package StepDefinition;

import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.runner.RunWith;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.AddPlaceAPI;
import resources.Utils;
import resources.resourcesAPI;

	@RunWith(Cucumber.class)
	public class stepDefinition extends Utils{
		RequestSpecification req,res;
		ResponseSpecification resp;
		Response response;
		JsonPath js;
		static String place_id; //share place id everywhere (for all testcases)
		
		AddPlaceAPI ad=new AddPlaceAPI(); // create an object from ADDplaceAPI	
		resourcesAPI resourceAPI;
		
	   @Given("Add Place was embeded in the body as payload with {string} {string} {string}")
	   public void add_place_was_embeded_in_the_body_as_payload_with(String name, String address, String language) throws IOException {
			 		
			 res=given().spec(RequestSpecification()) //calling RequestSpecification from Utils class
			.body(ad.AddPlacePOG(name,address,language));
		}
		
	 
	   @When("{string} request was sent as {string}  http prorocal")
	   public void request_was_sent_as_http_prorocal(String resource, String httpMethod) {
		    resourceAPI=resourcesAPI.valueOf(resource); // Initialize the constructor with the value of resource
		    System.out.println(resourceAPI.getResourcesAPI()); // get the resource value
		    if(httpMethod.equalsIgnoreCase("POST"))
			   response=res.when().post(resourceAPI.getResourcesAPI()).then().spec(ResponseSpecification()).extract().response(); 
		   if(httpMethod.equalsIgnoreCase("GET"))
			   response=res.when().get(resourceAPI.getResourcesAPI()).then().spec(ResponseSpecification()).extract().response(); 
		   if(httpMethod.equalsIgnoreCase("DELETE"))
			   response=res.when().delete(resourceAPI.getResourcesAPI()).then().spec(ResponseSpecification()).extract().response(); 	   
		}
		@Then("status code was successfully asserted at {int}")
		public void status_code_was_successfully_asserted_at(Integer num) {
			Assert.assertEquals(response.getStatusCode(),num.intValue());

		}
		@Then("{string} response in body is {string}")
		public void response_in_body_is(String key, String ExpectedValue) {
			
			String ActualValue=getJsonPath(response,key);
	        Assert.assertEquals(ExpectedValue, ActualValue);        
		}

		@Then("verify place_id maps to {string} using {string}")
		public void verify_place_id_maps_to_using(String expectedName, String resource) throws IOException {
			System.out.println("Passed name from feature file is "+expectedName);
		    // in order to get the place_id, first setup Uri and query through Specification
			place_id=getJsonPath(response,"place_id"); // extract place_id from previouse response
			//resourceAPI=resourcesAPI.valueOf(resource); and using get(resourceAPI.getResourcesAPI()) is one way of getting http method
			res=given().spec(RequestSpecification()) //calling RequestSpecification from Utils class
		    .queryParam("place_id", place_id);
			request_was_sent_as_http_prorocal( resource,"GET"); // call the request for http method with GET protoval
		    String ActualName=getJsonPath(response,"name"); // extract the name
		    System.out.println(ActualName);
		    Assert.assertEquals(expectedName, ActualName);
		}
		
		@Given("Delete Place API in the payload")
		public void delete_place_api_in_the_payload() throws IOException {
			System.out.println("Place_id string in Delete Place  is "+place_id);
		    res=given().spec(RequestSpecification()).body(ad.DeletePlaceIdPayload(place_id));
		    
		}
		@When("{string} is passed as {string} http protocal")
		public void is_passed_as_http_protocal(String resource, String string2) {
		    // Write code here that turns the phrase above into concrete actions
			request_was_sent_as_http_prorocal(resource, "DELETE");
		}
		@Then("status code was successfully asserted as {int}")
		public void status_code_was_successfully_asserted_as(Integer number) {
		    Assert.assertEquals(response.getStatusCode(), number.intValue());
		    
		}
		@Then("{string} response in the body is {string}")
		public void response_in_the_body_is(String status, String expectedStatus) {
		   String actualStatus=getJsonPath(response,status); //get the value of status
		    Assert.assertEquals(actualStatus, expectedStatus);
		    
		}




	}

