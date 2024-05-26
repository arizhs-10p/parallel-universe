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
import org.testng.annotations.*;

import java.time.Duration;

public class BaseClass {

    protected WebDriver driver;

    public static Logger log;

    ExtentSparkReporter htmlReporter;
    protected ExtentReports extent;
    protected ExtentTest logger;

    @BeforeMethod
    @Parameters("browser")
    public void parameterizedTest(String browser) {
        log = LogManager.getLogger(SauceDemoWebTest.class);
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            log.info("Firefox Browser initiated from base class");
            driver.manage().window().maximize();
            log.info("Firefox Browser maximize from base class");
            System.out.println("Browser Started :" + browser);

        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            log.info("Chrome Browser initiated from base class");
            driver.manage().window().maximize();
            log.info("Chrome Browser maximize from base class");
            System.out.println("Browser Started :" + browser);
        }


    }

    @BeforeClass
    public void setupExtentReport() {
        //Extent report
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/WebTestResults.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Full Stack QA");
        extent.setSystemInfo("Environment", "Web Automation Testing");
        extent.setSystemInfo("User Name", "Ariz Hussain Siddiqui");

        htmlReporter.config().setDocumentTitle("Title of the com.parallel.universe.base.Report Comes here");
        htmlReporter.config().setReportName("Name of the com.parallel.universe.base.Report Comes here");
        htmlReporter.config().setTheme(Theme.STANDARD);
    }


    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            //MarkupHelper is used to display the output in different colors
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
        driver.quit();
    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }


}

