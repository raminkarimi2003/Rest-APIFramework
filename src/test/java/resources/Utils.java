package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class Utils {
	public static RequestSpecification req;
	io.restassured.specification.ResponseSpecification resp;
	 JsonPath js;
    public RequestSpecification RequestSpecification() throws IOException
    {
     if (req==null)
      {	 
    	PrintStream log=new PrintStream("logging.txt");
    			req=new RequestSpecBuilder().setBaseUri(globalVar("baseURL")).addQueryParam("key","qaclick123")
    		    .addFilter(RequestLoggingFilter.logRequestTo(log)) //log request to logging.txt
    		    .addFilter(ResponseLoggingFilter.logResponseTo(log)) //log response to logging.txt
    			.setContentType(ContentType.JSON).build();
    	return req;
      }
	return req;
    }
    public ResponseSpecification ResponseSpecification()
    {
    	resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	return resp;
    }
    public static String globalVar(String key) throws IOException // read the property of globalURL.properties
    {
    	Properties pr=new Properties();
    	FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\globalURL.properties");
    	pr.load(fs);
    	return pr.getProperty(key); // getting the value
    	
    }
    public  String getJsonPath(Response res,String key)
    {
    	js=new JsonPath(res.asString());
    	String Key=js.get(key); // extract key from response
    	return Key;
    }
}
