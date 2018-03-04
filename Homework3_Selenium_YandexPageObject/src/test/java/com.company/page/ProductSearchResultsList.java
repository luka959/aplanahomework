package com.company.page;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductSearchResultsList extends CategoryMarketPage implements IProductSearchResult {
    private final String expectedProductName;

    public ProductSearchResultsList(WebDriver driver, String expectedProductName) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.expectedProductName = expectedProductName;
    }

    @Override
    public ProductCard getFirstResult() {
        List<WebElement> searchResults = getProducts();
        if (searchResults.isEmpty()) {
            Assert.fail("Товар не может быть найден");
        } else {
            searchResults.get(0).click();
        }
        return new ProductCard(driver, expectedProductName);
    }
}
