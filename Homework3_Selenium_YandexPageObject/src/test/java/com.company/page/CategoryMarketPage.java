package com.company.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    public CategoryMarketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AdvancedSearchMarketPage gotoAdvancedSearch() {
        advancedSearch.click();
        return new AdvancedSearchMarketPage(driver);
    }

    public CategoryMarketPage setListMode() {
        WebElement checkElement = driver.findElement(
                By.xpath("//input[@class='radio-button__control' and @value='list']")
        );
        String checkedValue = checkElement.getAttribute("checked");
        if (checkedValue != null && checkedValue.equals("checked")) {
            return this;
        }
        checkElement.click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(XPATH_FOR_ELEMENTS_LIST));
        return this;
    }

    public CategoryMarketPage set12PerPage() {
        productsPerPage.click();
        driver.findElement(By.xpath("//span[@class='select__text' and text()='Показывать по 12']")).click();
        new WebDriverWait(driver, 15)
                .until(
                        ExpectedConditions.numberOfElementsToBeLessThan(
                                XPATH_FOR_ELEMENTS_LIST,
                                13
                        )
                );
        return this;
    }

    public CategoryMarketPage checkProductsCount(int productsCount) {
        List<WebElement> products = getProducts();
        Assert.assertEquals("Количество предложений отличается", productsCount, products.size());
        return this;
    }

    protected List<WebElement> getProducts() {
        return driver.findElements(XPATH_FOR_ELEMENTS_LIST);
    }

    public IProductSearchResult findFirstProduct() {
        String productName = driver.findElement(XPATH_FOR_ELEMENTS_LIST).getText();
        searchInput.clear();
        searchInput.sendKeys(productName);
        applySearch.click();
        ProductCard productCard = new ProductCard(driver, productName);
        return productCard.isCorrect() ? productCard : new ProductSearchResultsList(driver, productName);
    }
}
