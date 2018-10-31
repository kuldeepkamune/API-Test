
import API.APIMethods;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class testAPI {

    @Test
    public void testGet()
    {
        //Get Response from Get method
        Response response = APIMethods.getMethod("posts");

        //Validate Response status code
        APIMethods.validateStatusCode(response,200);
    }

    @Test
    public void testPost()
    {
        Response response = APIMethods.postMethods("posts");

        //Validate Response status code
        APIMethods.validateStatusCode(response,201);

        //Validate any key-value from Response body
        APIMethods.validateResponseValue(response, "userId","1");
    }
}
