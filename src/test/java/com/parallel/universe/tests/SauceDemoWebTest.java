package com.parallel.universe.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.parallel.universe.base.BaseClass;
import com.parallel.universe.config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.parallel.universe.pages.LoginPage;
import com.parallel.universe.pages.ProductPage;

public class SauceDemoWebTest extends BaseClass {

    public static Logger log;
    //log = LogManager.getLogger(SauceDemoWebTest.class);
    //log= LogManager.getLogger(BaseClass.class);
    private ConfigReader configReader = new ConfigReader();
    String Url3 = configReader.getBaseUrl3();
    String username = configReader.getUsername();
    String password = configReader.getPassword();
    String usernameInvalid = configReader.getUsernameInvalid();
    String passwordInvalid = configReader.getPasswordInvalid();


    @Test
    public void loginTest() {

        log = LogManager.getLogger(SauceDemoWebTest.class);
        log.info("Driver initiated");
        // Create an instance of the LoginPage
        LoginPage loginPage = new LoginPage(driver);
        logger = extent.createTest("Open SauceDemo", "Test to validate valid login of sauce demo ");
        logger.log(Status.INFO, "Starting test loginTest");
        // Open the website
        driver.get(Url3);
        // Verify the title
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        log.info("Title verified");
        // Perform login
        loginPage.login(username, password);
        //Extent com.parallel.universe.base.Report Success Log
        logger.pass("Sauce Demo, Login Successful with title verify");

        // Add assertions or further verifications as needed
        // For example, you can verify that the login was successful by checking the presence of a dashboard element.
    }

    @Test
    public void loginTestFailed() {
        // Create an instance of the LoginPage
        LoginPage loginPage = new LoginPage(driver);
        logger = extent.createTest("Open SauceDemo", "Test to validate login with unmatched title");
        logger.log(Status.INFO, "Starting test loginTestFailed");
        // Open the website
        driver.get(Url3);
       logger.fail("Sauce Demo, Login UnSuccessful because of wrong title");
        // Verify the title
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        // Perform login
        loginPage.login(username, password);
        //Extent com.parallel.universe.base.Report Success Log
       // extlogin.pass("Sauce Demo, Login UnSuccessful because of wrong title");

        // Add assertions or further verifications as needed
        // For example, you can verify that the login was successful by checking the presence of a dashboard element.
    }

    @Test
    public void productSelect() throws InterruptedException {
        loginTest();
        ProductPage productPage = new ProductPage(driver);
        Thread.sleep(5000);
        productPage.setProductSelect();

    }


}


