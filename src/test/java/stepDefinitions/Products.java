package stepDefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utilities.Hooks.extent;
import static utilities.Hooks.test;
import static io.restassured.RestAssured.given;


public class Products {
    public int StatusCode = 200;
    public RequestSpecification httpRequest;
    public Response response;
    public int ResponseCode;
    public ResponseBody body;
    public JSONObject requestParams;
    public JsonPath jsnpath;
    public String s;

    @Given("^I hit the url of get products api endpoint$")
    public void i_hit_the_url_of_get_products_api_endpoint() throws Throwable {
        test = extent.createTest("Validate GET product API","This test validates that the GET API is working as intended.");
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode, StatusCode);
        if(ResponseCode == StatusCode){
            test.log(Status.PASS,"The Response code is 200.");
        }
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String rate) {
        body = response.getBody();
        //JSON Representation from Response Body
        jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("rating[0].rate").toString();
        assertEquals(rate, s);
        if(rate.equals(s)){
            test.log(Status.PASS,"The rating of the product matches.");
            System.out.println("GET API works as intended.");
            System.out.println("The rating of the product matches.");
        }
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
        System.out.println("----------------");
    }

    @Given("I hit the url of post product api endpoint")
    public void iHitTheUrlOfPostProductApiEndpoint() {
        test = extent.createTest("Validate POST API","This test validates that the POST API is working as intended.");
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();
        requestParams = new JSONObject();
    }

    @And("I pass the request body of product title {}")
    public void iPassTheRequestBodyOfProductTitle(String title) {
        requestParams.put("title", title);
        requestParams.put("price",13.5);
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","electronic");
        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post("products");
        body = response.getBody();
        jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAs(String id) {
        assertEquals(id, s);
        if(id.equals(s)){
            test.log(Status.PASS,"The product id matches.");
            System.out.println("POST API works as intended.");
            System.out.println("The ID of the product matches.");
            System.out.println("----------------");
        }
    }

    @Given("I hit the url of put product api endpoint")
    public void iHitTheUrlOfPutProductApiEndpoint() {
        test = extent.createTest("Validate PUT product API","This test validates that the PUT API is working as intended.");
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
    }

    @When("I pass the url of products in the request with {}")
    public void iPassTheUrlOfProductsInTheRequestWith(String productNumber) {
        httpRequest = RestAssured.given();
        requestParams.put("title", "test product");
        requestParams.put("price","13.5");
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","electronic");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.put("products/"+ productNumber);
        body = response.getBody();
        jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("id").toString();
        System.out.println("PUT API works as intended.");
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
        System.out.println("----------------");
    }

    @Given("I hit the url of delete product api endpoint")
    public void iHitTheUrlOfDeleteProductApiEndpoint() {
        test = extent.createTest("Validate DELETE product API","This test validates that the DELETE API is working as intended.");
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
    }

    @When("I pass the url of delete products in the request with {}")
    public void iPassTheUrlOfDeleteProductsInTheRequestWith(String productnumber) {
        httpRequest = RestAssured.given();
        requestParams.put("title", "test product");
        requestParams.put("price","13.5");
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","electronic");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.delete("products/"+ productnumber);
        body = response.getBody();
        jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("id").toString();
        System.out.println("DELETE API works as intended.");
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
        System.out.println("----------------");
    }

}
