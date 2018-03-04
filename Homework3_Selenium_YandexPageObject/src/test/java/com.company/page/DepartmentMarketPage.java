package com.company.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepartmentMarketPage extends BasePage {
    @FindBy(className = "catalog-menu")
    private WebElement categoriesPanel;

    public DepartmentMarketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CategoryMarketPage gotoCategory(String categoryName) {
        categoriesPanel.findElement(
                By.xpath(".//div[@class='catalog-menu__list']/a[text()='" + categoryName + "']")
        ).click();
        return new CategoryMarketPage(driver);
    }
}
