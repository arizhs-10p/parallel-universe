package com.parallel.universe.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.parallel.universe.config.ConfigReader;
import com.parallel.universe.tests.APITestScenarios;
import com.parallel.universe.tests.SauceDemoWebTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import com.parallel.universe.models.JwtTokenRequest;
import com.parallel.universe.models.JwtTokenResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BaseClassApi {

    public static Logger log;

    ExtentSparkReporter htmlReporter;
    protected ExtentReports extent;
    protected ExtentTest logger;
    protected JwtTokenResponse jwtTokenResponse;

        ConfigReader configReader = new ConfigReader();
        String HeaderAuth1 = configReader.getApiHeaderAuth1();
        String HeaderAuth2 = configReader.getApiHeaderAuth2();
        String ApiBaseURL = configReader.getBaseApiUrl();
        String EmailAddress = configReader.getApiEmailAddress();
        String DeviceSessionId = configReader.getApiDeviceSessionId();
        String Password = configReader.getApiPassword();
    @BeforeClass
    public void setupExtentReport()
    {
        //Extent report
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/ApiTestResults.html");
        extent = new ExtentReports ();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Full Stack QA");
        extent.setSystemInfo("Environment", "Automation Testing API");
        extent.setSystemInfo("User Name", "Ariz Hussain Siddiqui");

        htmlReporter.config().setDocumentTitle("Title of the com.parallel.universe.base.Report Comes here");
        htmlReporter.config().setReportName("Name of the com.parallel.universe.base.Report Comes here");
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }
    @BeforeMethod
    public void beforeMethod() throws Exception {
        System.out.println("I am in start of beforeMethod - API");
        log = LogManager.getLogger(APITestScenarios.class);
        log.info("JwtToken authentication initialize");
        JwtTokenRequest jwtTokenRequest = new JwtTokenRequest(EmailAddress, DeviceSessionId, Password);
        Response response = given().header(HeaderAuth1 , HeaderAuth2)
                .body(jwtTokenRequest).when().post(ApiBaseURL+"v1/authenticate").then().extract().response();

        jwtTokenResponse = response.getBody().as(new TypeRef<JwtTokenResponse>() {});
        log.info("JwtToken authentication completed");
        System.out.println(jwtTokenResponse.getCountry());
        System.out.println(jwtTokenRequest.getDeviceSessionId());
        System.out.println(jwtTokenRequest.getEmailAddress());
        //Report setup


    }

    @AfterMethod
    public void getResult(ITestResult result)
    {
        System.out.println("I am in afterMethod - API");
        if(result.getStatus() == ITestResult.FAILURE){
            //logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
            //MarkupHelper is used to display the output in different colors
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }else if(result.getStatus() == ITestResult.SKIP){
            //logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
    }
    @AfterTest
    public void endReport(){
        //driver.quit();
        extent.flush();
    }


/*
    @AfterMethod
    public void closingBrowser(){
        driver.quit();
        Reporter.log("Browser closed",true);
    //Extent report flush
        extent.flush();
        System.out.println("extent close");

    }

 */




}
