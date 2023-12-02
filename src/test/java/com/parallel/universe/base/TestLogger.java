package com.parallel.universe.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class TestLogger {

    public static WebDriver driver;
    public static Logger log;
    public static void main(String[] args) {

        log= LogManager.getLogger(TestLogger.class);

        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.info("Chrome driver setup complete");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Implicitly wait of 10 seconds");
        driver.get("https://www.myntra.com/");
        log.info("Logged into Myntra");
        try {
            boolean text= driver.findElement(By.xpath("//a[contains(text(),'Women')]")).isDisplayed();
        }
        catch(Exception e) {
            System.out.print(e.getMessage());
            log.error("Exception occured", new Exception("Element Not Found"));
        }
        finally {
            driver.quit();
            log.info("Quitting the driver");
        }
    }
}
