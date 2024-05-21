package com.parallel.universe.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.parallel.universe.util.Constant.*;
import static java.lang.System.getProperty;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        var env = System.getenv("SauceURL");
        System.out.println("testenv " + env);
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
        return System.getenv(WEB_BASE_URL);
    }

    public String getUsername() {
        return System.getenv(WEB_USERNAME);
    }

    public String getPassword() {
        return System.getenv(WEB_PASSWORD);
    }

    public String getUsernameInvalid() {
        return System.getenv(WEB_USERNAME_INVALID);
    }

    public String getPasswordInvalid() {
        return System.getenv(WEB_PASSWORD_INVALID);
    }

    //API
    public String getBaseApiUrl() {
        return System.getenv(API_BASE_URL);
    }

    public String getAuthenticateApiRoute() {
        return System.getenv(AUTHENTICATE_API_ROUTE);
    }

    public String getApiEmailAddress() {
        return System.getenv(API_EMAIL);
    }

    public String getApiDeviceSessionId() {
        return System.getenv(API_DEVICE_ID);
    }

    public String getApiPassword() {
        return System.getenv(API_PASSWORD);
    }

    public String getApiHeaderAuth1() {
        return properties.getProperty("HeaderAuth1");
    }

    public String getApiHeaderAuth2() {
        return properties.getProperty("HeaderAuth2");
    }

    public String getApiHeader1() {
        return properties.getProperty("Header1");
    }

    public String getApiHeader2() {
        return properties.getProperty("Header2");
    }

    public String getApiHeader3() {
        return properties.getProperty("Header3");
    }

    public String getApiHeader4() {
        return properties.getProperty("Header4");
    }

    public String getApiScenario() {
        return System.getenv(SCENARIO_ID_QUERY_PARAM);
    }

    public String getApiCompany() {
        return System.getenv(COMPANY_ID_QUERY_PARAM);
    }

    public String getDashboardApiRoute() {
        return System.getenv(DASHBOARD_API_ROUTE);
    }

    public String testDataExcelFile() {
        return System.getenv(TESTDATA_EXCEL_FILE);
    }

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

