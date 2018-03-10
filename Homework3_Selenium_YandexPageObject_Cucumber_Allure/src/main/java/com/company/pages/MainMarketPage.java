package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainMarketPage extends BasePage {
    @FindBy(className = "topmenu__list")
    public List<WebElement> departmentsContainer;

    @FindBy(xpath = "//button/span[contains(text(),'Все категории')]/parent::button")
    public List<WebElement> allDepartment;

    @FindBy(xpath = "//span[contains(@class, 'n-region-notification__ok')]")
    public List<WebElement> regionNotification;

    public void goToDepartmentUseAllDepartments(String departmentName) {
        By departmentXPath = By.xpath(
                "//div[@class='n-navigation-vertical-category']/a/span[text()='" + departmentName + "']/parent::a"
        );
        allDepartment.get(0).click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(departmentXPath))
                .click();
    }

    public void goToDepartmentFromTopMenu(String departmentName) {
        departmentsContainer.get(0)
                .findElement(By.xpath(".//a[text()='" + departmentName + "']"))
                .click();
    }
}
