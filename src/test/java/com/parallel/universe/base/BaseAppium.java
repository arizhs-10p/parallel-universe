package com.parallel.universe.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.parallel.universe.config.ConfigReader;
import com.parallel.universe.tests.AppiumTestScenarios;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseAppium {

    protected AndroidDriver androidDriver;
    public static Logger log2;

    ExtentSparkReporter htmlReporter2;
    protected ExtentReports extent2;
    protected ExtentTest logger2;


    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("I am in beforeMethod - Appium");
        log2 = LogManager.getLogger(AppiumTestScenarios.class);
        //Appium server starting
        log2.info("Appium server starting");
        AppiumServer.start();
        //Handling capabilities

    }
    public static AndroidDriver desiredCapabilities() throws MalformedURLException {
        ConfigReader configReader = new ConfigReader();
        String AppiumServerUrl = configReader.GetAppiumServerURL();
        String PlatformName = configReader.GetPlatformName();
        String PlatformVersion = configReader.GetPlatformVersion();
        String DeviceName = configReader.GetDeviceName();
        String AutomationName = configReader.GetAutomationName();

        File file = new File("src", "ApiDemos-debug.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
        cap.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName);

        AndroidDriver driver = new AndroidDriver(new URL(AppiumServerUrl), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
        return driver;
    }
    @BeforeClass
    public void setupExtentReport()
    {
        //Extent report
        htmlReporter2 = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/AppiumTestResults.html");
        extent2 = new ExtentReports ();
        extent2.attachReporter(htmlReporter2);
        extent2.setSystemInfo("Host Name", "Full Stack QA");
        extent2.setSystemInfo("Environment", "Automation Testing on pixel");
        extent2.setSystemInfo("User Name", "Ariz Hussain Siddiqui");

        htmlReporter2.config().setDocumentTitle("Title of the com.parallel.universe.base.Report Comes here");
        htmlReporter2.config().setReportName("Name of the com.parallel.universe.base.Report Comes here");
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter2.config().setTheme(Theme.STANDARD);
    }

    @AfterMethod

    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            //logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
            //MarkupHelper is used to display the output in different colors
            logger2.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger2.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }
        else if(result.getStatus() == ITestResult.SKIP){
            //logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
            logger2.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
    }

    public void afterMethod()
    {
        System.out.println("I am in afterMethod - Appium");
        androidDriver.quit();
        AppiumServer.stop();
    }
    @AfterTest
    public void endReport(){
        //driver.quit();
        extent2.flush();
    }
}
