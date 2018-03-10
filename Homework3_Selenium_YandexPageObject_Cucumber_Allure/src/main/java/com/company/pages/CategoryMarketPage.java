package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CategoryMarketPage extends BasePage {
    private static final By XPATH_FOR_ELEMENTS_LIST = By.xpath(
            "//div[contains(concat(' ', @class, ' '), ' n-snippet-card2 ')]//div[@class='n-snippet-card2__title']//a"
    );
    @FindBy(xpath = "//a[text()='Перейти ко всем фильтрам']")
    private WebElement advancedSearch;

    @FindBy(xpath = "//*[@class='select__control']/..")
    private WebElement productsPerPage;

    @FindBy(id = "header-search")
    private WebElement searchInput;

    @FindBy(xpath = "//span[@class='search2__button']/button")
    private WebElement applySearch;

    public void gotoAdvancedSearch() {
        advancedSearch.click();
    }

    public boolean isListViewMode() {
        WebElement checkedElement = driver.findElement(
                By.xpath("//input[@class='radio-button__control' and @checked='checked']")
        );
        return "list".equals(checkedElement.getAttribute("value"));
    }

    public CategoryMarketPage setListViewMode() {
        WebElement checkElement = driver.findElement(
                By.xpath("//input[@class='radio-button__control' and @value='list']")
        );
        checkElement.click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(XPATH_FOR_ELEMENTS_LIST));
        return this;
    }

    public CategoryMarketPage setElementsPerPage(int elementsPerPage) {
        productsPerPage.click();
        driver
                .findElement(
                        By.xpath("//span[@class='select__text' and text()='Показывать по " + elementsPerPage + "']")
                )
                .click();
        new WebDriverWait(driver, 15)
                .until(
                        ExpectedConditions.numberOfElementsToBeLessThan(
                                XPATH_FOR_ELEMENTS_LIST,
                                elementsPerPage + 1
                        )
                );
        return this;
    }

    protected List<WebElement> getProducts() {
        return driver.findElements(XPATH_FOR_ELEMENTS_LIST);
    }

    public int getProductsCount() {
        return getProducts().size();
    }

    public String getFirstProductName() {
        return driver.findElement(XPATH_FOR_ELEMENTS_LIST).getText();
    }

    public void enterToSearch(String value) {
        searchInput.clear();
        searchInput.sendKeys(value);
    }

    public void applySearch() {
        applySearch.click();
    }

    public WebElement getFirstProduct() {
        return driver.findElement(XPATH_FOR_ELEMENTS_LIST);
    }
}
