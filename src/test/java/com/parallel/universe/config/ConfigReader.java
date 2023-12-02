package com.parallel.universe.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(getProperty("Url1"));
    }

//Web
    public String getBaseUrl1() {
        return properties.getProperty("Url1");
    }
    public String getBaseUrl2() {
        return properties.getProperty("Url2");
    }
    public String getBaseUrl3() {
        return properties.getProperty("Url3");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
    public String getUsernameInvalid() {
        return properties.getProperty("usernameInvalid");
    }

    public String getPasswordInvalid() {
        return properties.getProperty("passwordInvalid");
    }

    //API
    public String getBaseApiUrl() {
        return properties.getProperty("apiBaseUrl");
    }
    public String getApiEmailAddress() {
        return properties.getProperty("ApiEmailAddress");
    }

    public String getApiDeviceSessionId(){return properties.getProperty("ApiDeviceSessionId");}
    public String getApiPassword(){return properties.getProperty("ApiPassword");}

    public String getApiHeaderAuth1(){return properties.getProperty("HeaderAuth1");}
    public String getApiHeaderAuth2(){return properties.getProperty("HeaderAuth2");}

    public String getApiHeader1(){return properties.getProperty("Header1");}
    public String getApiHeader2(){return properties.getProperty("Header2");}

    public String getApiHeader3(){return properties.getProperty("Header3");}
    public String getApiHeader4(){return properties.getProperty("Header4");}

    public String getApiScenario(){return properties.getProperty("scenarioId");}
    public String getApiCompany(){return properties.getProperty("companyId");}


    //Appium
    public String GetAppiumServerURL() {
        return properties.getProperty("appiumServerUrl");
    }

    public String GetPlatformName() {
        return properties.getProperty("platformName");
    }
    public String GetPlatformVersion() {
        return properties.getProperty("platformVersion");
    }

    public String GetDeviceName() {
        return properties.getProperty("deviceName");
    }
    public String GetAutomationName() {
        return properties.getProperty("automationName");
    }

}

