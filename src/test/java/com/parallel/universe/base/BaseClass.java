package com.parallel.universe.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.parallel.universe.tests.SauceDemoWebTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class BaseClass {

    protected WebDriver driver;
    //ExtentSparkReporter htmlReporter;
    //public static ExtentReports extent;
    //protected ExtentTest test1, test2;
    public static Logger log;

    ExtentSparkReporter htmlReporter;
    protected ExtentReports extent;
    protected ExtentTest logger;
    @BeforeMethod
   /* @Parameters("browser")
    public void parameterizedTest(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            System.out.println("Browser Started :" + browser);

        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("Browser Started :" + browser);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    */

        public void setupBrowser(){
        log = LogManager.getLogger(SauceDemoWebTest.class);
        //Reporter.log("Browser session started",true);
        //create ExtentReports and attach reporter(s)
        //htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport.html");
        //extent = new ExtentReports();
        //extent.attachReporter(htmlReporter);
        //Driver initialization

        driver = new ChromeDriver();
        log.info("Chrome browser initiated from base class");
        driver.manage().window().maximize();
        log.info("Chrome browser maximize from base class");
//        //Extent report
//        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/AhsExtentReport.html");
//        extent = new ExtentReports ();
//        extent.attachReporter(htmlReporter);
//        extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
//        extent.setSystemInfo("Environment", "Automation Testing");
//        extent.setSystemInfo("User Name", "Rajkumar SM");
//
//        htmlReporter.config().setDocumentTitle("Title of the com.parallel.universe.base.Report Comes here");
//        htmlReporter.config().setReportName("Name of the com.parallel.universe.base.Report Comes here");
//        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
//        htmlReporter.config().setTheme(Theme.STANDARD);
    }


    @BeforeClass
public void setupExtentReport()
{
    //Extent report
    htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/AhsExtentReport.html");
    extent = new ExtentReports ();
    extent.attachReporter(htmlReporter);
    extent.setSystemInfo("Host Name", "Full Stack QA");
    extent.setSystemInfo("Environment", "Automation Testing");
    extent.setSystemInfo("User Name", "Ariz Hussain Siddiqui");

    htmlReporter.config().setDocumentTitle("Title of the com.parallel.universe.base.Report Comes here");
    htmlReporter.config().setReportName("Name of the com.parallel.universe.base.Report Comes here");
    //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.STANDARD);
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

    @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            //logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
            //MarkupHelper is used to display the output in different colors
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }else if(result.getStatus() == ITestResult.SKIP){
            //logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
        driver.quit();
    }
    @AfterTest
    public void endReport(){
        //driver.quit();
        extent.flush();
    }


}

