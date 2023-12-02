package com.parallel.universe.tests;

import com.parallel.universe.base.BaseAppium;
import org.testng.annotations.Test;
import com.parallel.universe.pages.mobile.PreferencePage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class AppiumTestScenarios extends BaseAppium {

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
        // Create an instance of the PreferencePage
        androidDriver = desiredCapabilities();
        PreferencePage preferencePage = new PreferencePage(androidDriver);
        androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        preferencePage.preference("Test");
        //preferencePage.ClickPreferencePage();

        System.out.println("pref1");
        // Perform test
        //androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();

        System.out.println("pref2");

        // Add assertions or further verifications as needed
        // For example, you can verify that the login was successful by checking the presence of a dashboard element.
    }


    /*
    public static AndroidDriver desiredCapabilities() throws MalformedURLException {

//        AndroidDriver<AndroidElement> driver = null;
//        try {
        File file = new File("src", "ApiDemos-debug.apk");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
//            cap.setCapability("–session-override",true);
//            cap.setCapability("fullReset", true);
        cap.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
        return driver;
    }

     */

}
