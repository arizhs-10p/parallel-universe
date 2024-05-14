package com.parallel.universe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    By ProductSelect = By.id("item_1_title_link");
    private WebDriver driver;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProduct() {
        driver.findElement(ProductSelect).click();
    }

    public void setProductSelect() {
        clickProduct();
    }
}
