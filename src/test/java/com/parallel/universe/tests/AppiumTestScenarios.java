package com.parallel.universe.tests;

import com.aventstack.extentreports.Status;
import com.parallel.universe.base.BaseAppium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.parallel.universe.pages.mobile.PreferencePage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class AppiumTestScenarios extends BaseAppium {

    public static Logger log2;
   /* @Test
    public void testOne() throws MalformedURLException {
        //AppiumServer.start();
        androidDriver = desiredCapabilities();
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
        androidDriver.findElement(By.id("android:id/checkbox")).click();
//        driver.findElementByXPath("//android.widget.LinearLayout[@index='2']").click();
//        Thread.sleep(10000);

        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='WiFi settings']")).click();

        androidDriver.findElement(By.id("android:id/edit")).sendKeys("Test");
        androidDriver.findElement(By.id("android:id/button1")).click();
        //AppiumServer.stop();
    }

    */


    @Test
    public void PreferenceTest() throws MalformedURLException {

        log2 = LogManager.getLogger(AppiumTestScenarios.class);
        log2.info("Appium Test Initiated");
        // Create an instance of the PreferencePage
        androidDriver = desiredCapabilities();
        logger2 = extent2.createTest("Open Appium APK", "Test to validate preference page");
        logger2.log(Status.INFO, "Starting test preference page");
        PreferencePage preferencePage = new PreferencePage(androidDriver);
        androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        preferencePage.preference("Test");
        //preferencePage.ClickPreferencePage();
        log2.info("Appium test executed");
        //Extent com.parallel.universe.base.Report Success Log
        logger2.pass("Appium APK, Test successful");
        System.out.println("preference test completed");

        // Add assertions or further verifications as needed
        // For example, you can verify that the login was successful by checking the presence of a dashboard element.
    }


}
