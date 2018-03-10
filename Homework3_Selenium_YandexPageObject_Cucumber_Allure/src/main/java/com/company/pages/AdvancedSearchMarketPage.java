package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdvancedSearchMarketPage extends BasePage {
    @FindBy(id = "glf-pricefrom-var")
    private WebElement minCost;

    @FindBy(xpath = "//span[text()='Показать подходящие']/parent::a")
    private WebElement applyButton;

    public void setMinCost(String cost) {
        minCost.sendKeys(cost);
    }

    public void selectVendor(String vendor) {
        By vendorCheckboxXPath = By.xpath(
                "//label[@class='checkbox__label' and contains(@for, '7893318') and text()='" + vendor + "']"
        );
        List<WebElement> elements = driver.findElements(vendorCheckboxXPath);
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }

    public void fillField(String field, String value) {
        switch (field) {
            case "Цена от":
                setMinCost(value);
                break;
            case "Производитель":
                selectVendor(value);
                break;
            default:
                throw new IllegalArgumentException("Поле '" + field + "' неизвестно");
        }
    }

    public void applySearch() {
        applyButton.click();
    }
}
