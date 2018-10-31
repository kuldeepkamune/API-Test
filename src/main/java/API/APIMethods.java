package API;

import Utilities.PropertiesFile;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class APIMethods {

    /*
    Method: To get Response from Get method
    Parameters: Method name
    Return: Response from Get method
     */
    public static Response getMethod(String methodName)
    {
        try {
            RestAssured.baseURI = PropertiesFile.readPropertiesValue("URL");

            // Get the RequestSpecification of the request that you want to sent
            // to the server. The server is specified by the BaseURI that we have
            // specified in the above step.
            RequestSpecification httpRequest = RestAssured.given();

            // Make a request to the server by specifying the method Type and the method URL.
            // This will return the Response from the server. Store the response in a variable.
            Response response = httpRequest.request(Method.GET, "/" + methodName);

            // Now let us print the body of the message to see what response

            String responseBody = response.getBody().asString();
            System.out.println("Response Body is =>  " + responseBody);

            return response;
        }
        catch (Exception ex)
        {
            System.out.println("Exception in getMethod"+ex.toString());
            return null;
        }
    }

    /*
    Method: To get Post method Response
    Parameters: method name
    Return: Response from Post method
     */
    public static Response postMethods(String methodName)
    {
        try {
            RestAssured.baseURI = PropertiesFile.readPropertiesValue("URL");

            RequestSpecification httpRequest = RestAssured.given();

            JSONObject requestParams = new JSONObject();

            //Insert key-value in Post request body
            requestParams.put("title", "foo");
            requestParams.put("body", "bar");
            requestParams.put("userId", "1");

            // Add a header stating the Request body is a JSON
            httpRequest.header("Content-Type", PropertiesFile.readPropertiesValue("contentType"));

            // Add the Json to the body of the request
            httpRequest.body(requestParams.toJSONString());

            // Post the request and check the response
            Response response = httpRequest.post("/" + methodName);

            String responseBody = response.getBody().asString();
            System.out.println("Response Body is =>  " + responseBody);

            return response;
        }
        catch (Exception ex)
        {
            System.out.println("Exception in postMethods"+ex.toString());
            return null;
        }
    }

    /*
    Method: To verify Response status code
    Parameters: Response from API, expected status code
    Return: Null
     */
    public static void validateStatusCode(Response response, int expectedStatusCode)
    {
        //Get Response status code
        int actualStatusCode = response.getStatusCode();
        System.out.println("Actual Response Status is =>  " + actualStatusCode);
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    /*
    Method: To verify any particular value from Response body
    Parameters: Response from API, key value, expected value
    Return: Null
     */
    public static void validateResponseValue(Response response, String keyValue, String expectedValue)
    {
        //Get userID value from Response
        String actualKeyValue = response.jsonPath().get(keyValue);
        System.out.println(keyValue+" value =>  " + actualKeyValue);
        Assert.assertEquals(actualKeyValue, expectedValue);
    }
}
