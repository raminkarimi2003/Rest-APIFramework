Feature: Add APi place using google map

@AddPlace@Regression
Scenario Outline: Verifying Add Place API is successfully excecuted
Given Add Place was embeded in the body as payload with "<name>" "<Address>" "<language>"    
When "AddPlaceAPI" request was sent as "POST"  http prorocal
Then status code was successfully asserted at 200
And "status" response in body is "OK"
And "scope" response in body is "APP"
And verify place_id maps to "<name>" using "GetPlaceAPI"

Examples:
         |name        |Address       |language| 
         |Ramin Karimi|Kanata, Canada|Persian |
#         |Shabnam Dast|Kanata, Canada|Persian | 

@DeletePlace@Regression 
Scenario: Verifying Delete Place API is successfully excecuted
Given Delete Place API in the payload
When "DeletePlaceAPI" is passed as "GET" http protocal
Then status code was successfully asserted as 200
And "status" response in the body is "OK"
