package com.parallel.universe.pages;

import com.parallel.universe.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;
    private WebDriverHelper helper;

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        helper = new WebDriverHelper(driver);
    }

    // Actions
    public void enterUsername(String username) {

        //driver.findElement(usernameField).sendKeys(username);
        helper.waitForElementToBeVisible(usernameField).sendKeys(username);

    }

    public void enterPassword(String password) {

        //driver.findElement(passwordField).sendKeys(password);
        helper.waitForElementToBeVisible(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {

        //driver.findElement(loginButton).click();
    helper.waitForElementToBeClickable(loginButton).click();
    }

    // Login method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
