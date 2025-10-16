Feature: Validating place API's


Scenario: Verify if place is being successfully added using AddPaceAPI
     
      Given Add Place PayLoad
      When user calls "AddPlaceAPI" with post http request
      Then the API call got success with status code 200
      And "status" in response body is "OK"
      And "scope" in response body is "APP"