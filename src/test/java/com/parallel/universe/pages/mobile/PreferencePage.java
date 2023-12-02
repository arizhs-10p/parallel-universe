package com.parallel.universe.pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class PreferencePage {
    private final AndroidDriver androidDriver;
    private By preferencepage = By.xpath("//android.widget.TextView[@text='Preference']");
    private By preferencedependency = By.xpath("//android.widget.TextView[@text='3. Preference dependencies']");
    private By prefcheckbox = By.id("android:id/checkbox");
    private By prefwifi = By.xpath("//android.widget.TextView[@text='WiFi settings']");
    private By prefeditwifi = By.id("android:id/edit");
    private By prefbtnclick = By.id("android:id/button1");

            //Constructor and passing android driver
            public PreferencePage(AndroidDriver androidDriver)
            {
                this.androidDriver = androidDriver;
            }


            //Methods to initiate page objects
            public void ClickPreferencePage()
            {
                androidDriver.findElement(preferencepage).click();
            }
            public void ClickPreferenceDependency()
            {
            androidDriver.findElement(preferencedependency).click();
            }
            public void ClickPrefCheckbox()
            {
            androidDriver.findElement(prefcheckbox).click();
            }
            public void ClickPrefWifi()
            {
            androidDriver.findElement(prefwifi).click();
            }
            public void EnterPrefEditWifi(String test)
            {
            androidDriver.findElement(prefeditwifi).sendKeys(test);
            }
            public void ClickPrefBtn()
            {
            androidDriver.findElement(prefbtnclick).click();
            }
            public void preference(String test) {
                ClickPreferencePage();
                ClickPreferenceDependency();
                ClickPrefCheckbox();
                ClickPrefWifi();
                EnterPrefEditWifi(test);
                ClickPrefBtn();


            }
}
