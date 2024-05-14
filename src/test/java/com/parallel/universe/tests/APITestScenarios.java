package com.parallel.universe.tests;

import com.aventstack.extentreports.Status;
import com.parallel.universe.base.BaseClassApi;
import com.parallel.universe.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.parallel.universe.util.Constant.*;
import static io.restassured.RestAssured.given;

public class APITestScenarios extends BaseClassApi {

    public static Logger log;
    private ConfigReader configReader = new ConfigReader();
    String HeaderAuth1 = configReader.getApiHeaderAuth1();
    String HeaderAuth2 = configReader.getApiHeaderAuth2();
    String Header1 = configReader.getApiHeader1();
    String Header2 = configReader.getApiHeader2();
    String Header3 = configReader.getApiHeader3();
    String Header4 = configReader.getApiHeader4();
    String ApiBaseURL = configReader.getBaseApiUrl();
    String scenarioIDValue = configReader.getApiScenario();
    String companyIDValue = configReader.getApiCompany();
    String dashboardAPIRoute = configReader.getDashboardApiRoute();



//    @Test(dataProvider="Authentication02", dataProviderClass = UtilsReader.class)
//    public void apiTestOne(String emailAddress, String deviceSessionId, String password) {
//
//        System.out.println(emailAddress);
//        System.out.println(deviceSessionId);
//        System.out.println(password);
//
//
//    }


    /*
    @Test(dataProvider="Authentication", dataProviderClass = UtilsReader.class)
    public void DashboardLayoutFinmarkDP(String ScenarioID, String CompanyID)
    {
        log = LogManager.getLogger(APITestScenarios.class);
        log.info("API Finmark Dashboard Layout Test Start");
        logger = extent.createTest("Verifying Test", "Test to validate correct scenario ");
        logger.log(Status.INFO, "Starting test of API Dashboard layout");
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
        log.info("Status code verified");
        //Extent com.parallel.universe.base.Report Success Log
        logger.pass("API Dashboard layout test successful");
    }
    */


    @Test
    public void DashboardLayoutFinmarkCS() {
        log = LogManager.getLogger(APITestScenarios.class);
        log.info("API Finmark Dashboard Layout Test Start");
        logger = extent.createTest("Verifying Test", "Test to validate correct scenario ");
        logger.log(Status.INFO, "Starting test of API Dashboard layout");
        System.out.println(jwtTokenResponse.getJwtToken());
        System.out.println(jwtTokenResponse.userEmail);
        given()
                .queryParam(SCENARIO_ID_QUERY_PARAM, scenarioIDValue)
                .when()
                .header(HEADER_ACCEPT, HEADER_APPLICATION)
                .header(HEADER_AUTH, HEADER_BEARER + jwtTokenResponse.getJwtToken())
                .get(ApiBaseURL + dashboardAPIRoute)
                .then()
                .statusCode(200)
                .log()
                .all();
        log.info("Status code verified");
        //Extent com.parallel.universe.base.Report Success Log
        logger.pass("API Dashboard layout test successful");
    }

    @Test
    public void DashboardLayoutWithAssert() {
        RestAssured.baseURI = ApiBaseURL;
        RequestSpecification httpRequest = RestAssured.given()
                .queryParam(SCENARIO_ID_QUERY_PARAM, scenarioIDValue)
                .when()
                .header(HEADER_ACCEPT, HEADER_APPLICATION)
                .header(HEADER_AUTH, HEADER_BEARER + jwtTokenResponse.getJwtToken());
        Response response = (Response) httpRequest.get(dashboardAPIRoute);
        ResponseBody responseBody = response.getBody();
        String bodyAsString = responseBody.asString();
        System.out.println(bodyAsString);
        Assert.assertEquals(bodyAsString.contains("runway") /*Expected value*/, true /*Actual Value*/, "Response body contains Runway");
    }


}
