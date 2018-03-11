package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends BasePage {
    @FindBy(className = "bLeftMainMenu")
    private WebElement categoriesPanel;

    public void goToCategory(String categoryName) {
        categoriesPanel.findElement(
                By.xpath("//div[@class='bLeftMainMenu no-mobile']//a[contains(text(),'" + categoryName + "')]")
        ).click();
    }
}
