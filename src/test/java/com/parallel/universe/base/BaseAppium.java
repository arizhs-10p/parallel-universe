package com.parallel.universe.base;

import com.parallel.universe.config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseAppium {

    protected AndroidDriver androidDriver;


    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("I am in beforeMethod - Appium");
        //Appium server starting
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

    @AfterMethod
    public void afterMethod()
    {
        System.out.println("I am in afterMethod - Appium");
        androidDriver.quit();
        AppiumServer.stop();
    }
}
