package userManagement;

import helper.StatusCodesMessages;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pojo.AuthorDetailsPojo;
import utils.JSONReader;
import utils.PropertyReader;
import utils.SoftAssertUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;




public class GetUser {
    SoftAssertUtils softAssertUtils = new SoftAssertUtils();
    @Test
    public void getUserInfo()
    {
        given().
                when().
                get("https://reqres.in/api/users?page=2").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void validateGetResponseBodyEqualTo()
    {
        given().
                contentType("application/json").
                when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors/1")
                .then()
                .assertThat().statusCode(200)
                .body(not(isEmptyString()))
                .body("id", equalTo(1));

    }
    @Test
    public void validateGetResponseBodyContainsString()
    {
        given().
                contentType("application/json").
                when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors/1")
                .then()
                .assertThat().statusCode(200)
                .body(not(isEmptyString()))
                .body("firstName", containsString("First Name 1"));
    }

    @Test
    public void validateGetResponseBodyFullContainsString()
    {
        Response response_Stored =  given()
                .contentType("application/json")
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors");

        assertThat(response_Stored.statusCode(), is(200));
        assertThat(response_Stored.body().jsonPath().getString("firstName"), containsString("First Name 20"));
        //System.out.println(response_Stored.body().jsonPath().getString("firstName"));
    }
    @Test
    public void validateGetResponseBodyFullhasItem()
    {
        Response response_Stored = given()
                .contentType("/application/json")
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors");

        assertThat(response_Stored.statusCode(), is(200));
        assertThat(response_Stored.body().jsonPath().getList("firstName"), hasItem("First Name 21"));
    }
    @Test
    public void validateGetResponseBodyFullhasItems()
    {
        Response response_Stored = given()
                .contentType("/application/json")
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors");

        assertThat(response_Stored.statusCode(), is(200));
        assertThat(response_Stored.body().jsonPath().getList("firstName"), hasItems("First Name 22","First Name 23"));
    }

    @Test
    public void validateGetResponseBodyFullhasSize()
    {
        Response response_Stored = given()
                .contentType("application/json")
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors");

        assertThat(response_Stored.statusCode(), is(200));
        assertThat(response_Stored.body().jsonPath().getList(""), hasSize(596));
    }

    @Test
    public void validateGetResponseContains()
    {
        Response response_Stored = given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users?page=2");

        assertThat(response_Stored.statusCode(), is(200));
        assertThat(response_Stored.body().jsonPath().getList("data.id"), contains(7,8,9,10,11,12));
    }

    @Test
    public void validateGetResponseEqualtoIs()
    {
        Response response_Stored = given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users?page=2");

        assertThat(response_Stored.statusCode(), is(200));

        assertThat(response_Stored.body().jsonPath().getInt("data[0].id"), is(7));
        assertThat(response_Stored.body().jsonPath().getString("data[1].first_name"), equalTo("Lindsay"));
    }
    @Test
    public void validateGetQueryparam()
    {
        Response response_Stored = given()
                .contentType("application/json")
                .queryParam("page","2")
                .queryParam("sort","desc")//multiple query param
                .when()
                .get("https://reqres.in/api/users");
        assertThat(response_Stored.statusCode(), is(200));
        //System.out.println(response_Stored.body().jsonPath().getString(""));
    }

    @Test
    public void validateGetPathParam()
    {
        given().
                contentType("application/json")
                        .pathParam("id",1)
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors/{id}")
                .then()
                .assertThat().statusCode(200)
                .body(not(isEmptyString()))
                .body("firstName", containsString("First"));
    }
    @Test
    public void validatePostFormParam()
    {
        given().
                contentType("application/x-www-form-urlencoded")
                .formParam("name","ak")
                .formParam("email","ak@mail.com")
                .when()
                .post("https://formsubmit.co/your@email.com")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void validateMapMultiHeaders()
    {
        Map<String, String> headersMap = new HashMap<String, String>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("Accept", "application/json");
        headersMap.put("Authorization", "Bearer AAAAA5n0w5n0w5n0wMyL0ngBe4r4rT0k4n");

        Response response_stored =  given()
                .headers(headersMap)
                .when()
                .get("https://reqres.in/api/users");

                assertThat(response_stored.statusCode(), is(200));
                assertThat(response_stored.body(), notNullValue());
                assertThat(response_stored.header("Content-Type"), startsWith("application/json"));

                Headers headersCaptured = response_stored.headers();
                for (Header header : headersCaptured.asList())
                {
                    System.out.println(header);
                    if(header.getName().equals("Server"))
                    {
                        assertThat(header.getValue(),equalTo("cloudflare"));
                    }
                }
    }

    @Test
    public void getEnumStatusCode()
    {
        Response responseStored = given().
                when().
                get("https://reqres.in/api/users?page=2");
    assertThat(responseStored.statusCode(), equalTo(StatusCodesMessages.SUCCESS.getStatusCodes()));
    System.out.println(StatusCodesMessages.SUCCESS.getMessage());
        //assertThat().statusCode(200);
    }

    @Test
    public void basicAuthJSONData() throws IOException, ParseException {
        String username = JSONReader.getTestData("username");
        String password = JSONReader.getTestData("password");
        Response response_Stored = given()
                .auth()
                .basic(username, password)
                .when()
                .get("https://reqres.in/api/users?page=2");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
    @Test
    public void propertyFileData() throws IOException{
        String server_address = PropertyReader.PropertyReader(System.getProperty("user.dir")+"\\config.properties", "server_address");
        Response response_Stored = given()
                .when()
                .get(server_address);
        System.out.println("server address: " + server_address);
    }

    @Test
    public void propertyFileTestData() throws IOException, ParseException {
        String server_address = PropertyReader.PropertyReader(System.getProperty("user.dir")+"\\config.properties", "server_address");
        String author_endpoint = JSONReader.getTestData("author_endpoint");
        String url = server_address + author_endpoint;
        Response response_Stored = given()
                .when()
                .get(url);
        System.out.println("url: " +url);
    }

    @Test
    public void wrapperAssertion(){

        Response response_Stored = given()
                .when()
                .get("https://reqres.in/api/users?page=2");

        softAssertUtils.assertEquals(response_Stored.getStatusCode(), "Passed");
        System.out.println("https://reqres.in/api/users?page=2");
    }

    @Test
    public void bodyUsingPojo() throws IOException, ParseException {
        AuthorDetailsPojo authorDetailsPojo = new AuthorDetailsPojo();
        authorDetailsPojo.setFirstName("AK Name");
        authorDetailsPojo.setLastName("Sh Name");
        authorDetailsPojo.setId(01);
        authorDetailsPojo.setIdBook(1001);
        String server_address = PropertyReader.PropertyReader(System.getProperty("user.dir")+"\\config.properties", "server_address");
        String author_endpoint = JSONReader.getTestData("author_endpoint");
        String url = server_address + author_endpoint;

        Response response_Stored = given()
        .body(authorDetailsPojo)
                .when()
                .post(url);

        softAssertUtils.assertEquals(response_Stored.getStatusCode(), "Passed");

    }

}
