package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepartmentMarketPage extends BasePage {
    @FindBy(className = "catalog-menu")
    private WebElement categoriesPanel;

    public void goToCategory(String categoryName) {
        categoriesPanel.findElement(
                By.xpath(".//div[@class='catalog-menu__list']/a[text()='" + categoryName + "']")
        ).click();
    }
}
