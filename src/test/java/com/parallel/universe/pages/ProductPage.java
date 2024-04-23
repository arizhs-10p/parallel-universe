package com.parallel.universe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;

    By ProductSelect = By.id("item_1_title_link");


    public ProductPage(WebDriver driver)
    {
        this.driver = driver;
    }
    public void clickProduct()
    {
        driver.findElement(ProductSelect).click();
    }
    public void setProductSelect()
    {
        clickProduct();
    }
}
