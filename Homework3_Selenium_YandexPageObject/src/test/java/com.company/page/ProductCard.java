package com.company.page;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCard extends BasePage implements IProductSearchResult {
    private final String expectedProductName;
    @FindBy(xpath = "//div[@class='n-title__text']/h1")
    private List<WebElement> productTitle;

    public ProductCard(WebDriver driver, String expectedProductName) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.expectedProductName = expectedProductName;
    }

    public String getExpectedProductName() {
        return expectedProductName;
    }

    @Override
    public ProductCard getFirstResult() {
        return this;
    }

    public String getProductTitle() {
        return productTitle.isEmpty() ? null : productTitle.get(0).getText();
    }

    public boolean isCorrect() {
        return !productTitle.isEmpty();
    }

    public ProductCard checkProductName() {
        Assert.assertEquals("Наименования товаров не совпадают", expectedProductName, getProductTitle());
        return this;
    }
}
