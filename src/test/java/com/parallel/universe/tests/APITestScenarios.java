package com.parallel.universe.tests;

import com.parallel.universe.base.BaseClassApi;
import com.parallel.universe.config.ConfigReader;
import com.parallel.universe.models.JwtTokenRequest;
import com.parallel.universe.models.JwtTokenResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.parallel.universe.util.UtilsReader;

import java.net.Authenticator;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class APITestScenarios extends BaseClassApi {

    //JwtTokenResponse jwtTokenResponse;
private ConfigReader configReader = new ConfigReader();
String HeaderAuth1 = configReader.getApiHeaderAuth1();
String HeaderAuth2 = configReader.getApiHeaderAuth2();
String Header1 = configReader.getApiHeader1();
String Header2 = configReader.getApiHeader2();
String Header3 = configReader.getApiHeader3();
String Header4 = configReader.getApiHeader4();
String ApiBaseURL = configReader.getBaseApiUrl();
String ScenarioID = configReader.getApiScenario();
String CompanyID = configReader.getApiCompany();


/*
    @Test(dataProvider="Authentication", dataProviderClass = UtilsReader.class)
    public void apiTestOne(String emailAddress, String deviceSessionId, String password) {

        System.out.println(emailAddress);
        System.out.println(deviceSessionId);
        System.out.println(password);


    }

 */


    @Test(dataProvider="Authentication", dataProviderClass = UtilsReader.class)
    public void DashboardLayoutFinmarkWS(String emailAddress, String deviceSessionId, String password)
    {
        System.out.println("I am in testThree method");

               given()
                       .queryParam("scenarioId",deviceSessionId)
                       .queryParam("companyId",password)
                .when()
                .header(Header1,Header2)
                .header(Header3,Header4 + jwtTokenResponse.getJwtToken())
                //.get("https://tst1.finmark.com/api/v2/dashboard-layouts/all")
                       .get(ApiBaseURL+"v2/dashboard-layouts/all")
                .then()
                       .statusCode(400)
                .log()
                .all();
    }
    @Test
    public void DashboardLayoutFinmarkCS()
    {
        System.out.println("I am in Dashboard layout of finmark");

        given()
                .queryParam("scenarioId",ScenarioID)
                .queryParam("companyId",CompanyID)
                .when()
                .header(Header1,Header2)
                .header(Header3,Header4 + jwtTokenResponse.getJwtToken())
                //.get("https://tst1.finmark.com/api/v2/dashboard-layouts/all")
                .get(ApiBaseURL+"v2/dashboard-layouts/all")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void DashboardLayoutWithAssert()
    {
        RestAssured.baseURI = ApiBaseURL;
        RequestSpecification httpRequest = RestAssured.given()
                .queryParam("scenarioId",ScenarioID)
                .queryParam("companyId",CompanyID)
                .when()
                .header(Header1,Header2)
                .header(Header3,Header4 + jwtTokenResponse.getJwtToken());
        Response response = (Response) httpRequest.get("v2/dashboard-layouts/all");
        ResponseBody responseBody = response.getBody();
        String bodyAsString = responseBody.asString();
        System.out.println(bodyAsString);
        Assert.assertEquals(bodyAsString.contains("runway") /*Expected value*/, true /*Actual Value*/, "Response body contains Runway");
    }


}
