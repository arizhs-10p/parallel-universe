package com.parallel.universe.base;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report {

    ExtentSparkReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest logger;
    WebDriver driver;


    @BeforeTest
    public void startReport(){
        driver=new ChromeDriver();

        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/ExtentReport.html");
        extent = new ExtentReports ();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
        extent.setSystemInfo("Environment", "Automation Testing");
        extent.setSystemInfo("User Name", "Rajkumar SM");

        htmlReporter.config().setDocumentTitle("Title of the com.parallel.universe.base.Report Comes here");
        htmlReporter.config().setReportName("Name of the com.parallel.universe.base.Report Comes here");
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    @Test
    public void passTest(){
        logger = extent.createTest("passTest");
        Assert.assertTrue(true);
        logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is passTest", ExtentColor.GREEN));
    }
    @Test(priority = 0, description = "Test Done 11")
    public void yahoo()
    {
        logger = extent.createTest("Open Yahoo Website", "Test to validate website opening of yahoo ");
        logger.log(Status.INFO, "Starting test case testTwo");
        driver.get("https://www.yahoo.com/");
        //driver.manage().window().minimize();
        logger.log(Status.PASS, MarkupHelper.createLabel("yahoo passed", ExtentColor.GREEN));

    }

    @Test(priority = 0, description = "Test Done 11")
    public void sauce()
    {
        logger = extent.createTest("Open Sauce Website", "Test to validate website opening of sauce ");
        logger.log(Status.INFO, "Starting test case testTwo");
        driver.get("https://www.saucedemo.com/v1/");
        //driver.manage().window().minimize();
        logger.log(Status.PASS, MarkupHelper.createLabel("Test Case sauce passed", ExtentColor.GREEN));

    }

    @Test
    public void failTest(){
        logger = extent.createTest("failTest");
        Assert.assertTrue(false);
        logger.log(Status.PASS, "Test Case (failTest) Status is passed");
        logger.log(Status.PASS, MarkupHelper.createLabel("Test Case (failTest) Status is passed", ExtentColor.GREEN));
    }

    @Test
    public void skipTest(){
        logger = extent.createTest("skipTest");
        throw new SkipException("Skipping - This is not ready for testing ");
    }

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
    }
    @AfterTest
    public void endReport(){
        extent.flush();
    }


}