Feature: Validating place API's


@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPaceAPI
     
      Given Add Place PayLoad with "<name>" "<language>" "<address>"
      When user calls "AddPlaceAPI" with "Post" http request
      Then the API call got success with status code 200
      And "status" in response body is "OK"
      And "scope" in response body is "APP"
      And verify place_Id created maps to "<name>" using "GetPlaceAPI"
      
      Examples:
 |name    |language|address           |
 |House   |English |world cross centre|
 #|B-House |Spanish |sea cross centre  |
 
 
 
@DeletePlace
Scenario: Verify delete place functionlity is working	 
 	 Given DeletePlacePayload
 	 When user calls "DeletePlaceAPI" with "Post" http request
 	 Then the API call got success with status code 200
     And "status" in response body is "OK"
 	 