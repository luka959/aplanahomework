package com.company.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductCard extends BasePage {
    @FindBy(xpath = "//div[@class='n-title__text']/h1")
    private List<WebElement> productTitle;

    public ProductCard() {
    }

    public String getProductTitle() {
        return productTitle.get(0).getText();
    }

    public boolean isCorrect() {
        return !productTitle.isEmpty();
    }
}
