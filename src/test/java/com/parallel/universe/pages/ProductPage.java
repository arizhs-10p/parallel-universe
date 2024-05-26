package com.parallel.universe.pages;

import com.parallel.universe.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    By ProductSelect = By.id("item_1_title_link");
    private WebDriver driver;
    private WebDriverHelper helper;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        helper = new WebDriverHelper(driver);
    }

    public void clickProduct() {
        //driver.findElement(ProductSelect).click();
    helper.waitForElementToBeVisible(ProductSelect).click();
    }

   // public void setProductSelect() {
     //   clickProduct();
    //}
}
