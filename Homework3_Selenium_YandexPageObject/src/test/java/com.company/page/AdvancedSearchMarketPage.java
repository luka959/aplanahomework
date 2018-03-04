package com.company.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdvancedSearchMarketPage extends BasePage {
    @FindBy(id = "glf-pricefrom-var")
    private WebElement minCost;

    @FindBy(xpath = "//span[text()='Показать подходящие']/parent::a")
    private WebElement applyButton;

    public AdvancedSearchMarketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AdvancedSearchMarketPage setMinCost(int cost) {
        minCost.sendKeys(String.valueOf(cost));
        return this;
    }

    public AdvancedSearchMarketPage selectVendors(String... vendors) {
        for (int i = 0; i < vendors.length; i++) {
            String vendor = vendors[i];
            By vendorCheckboxXPath = By.xpath(
                    "//label[@class='checkbox__label' and contains(@for, '7893318') and text()='" + vendor + "']"
            );
            List<WebElement> elements = driver.findElements(vendorCheckboxXPath);
            if (!elements.isEmpty()) {
                elements.get(0).click();
            }
        }
        return this;
    }

    public CategoryMarketPage applySearch() {
        applyButton.click();
        return new CategoryMarketPage(driver);
    }
}
